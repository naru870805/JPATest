package kr.co.sinbuya.www.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.sinbuya.common.CookieHelper;
import kr.co.sinbuya.entity.Contacts;
import kr.co.sinbuya.entity.Guest;
import kr.co.sinbuya.entity.SiteChild;
import kr.co.sinbuya.entity.SubEvent;
import kr.co.sinbuya.repository.ContactsRepository;
import kr.co.sinbuya.repository.GuestRepository;
import kr.co.sinbuya.repository.SubEventRepository;
import kr.co.sinbuya.www.vo.ContactsVO;
import net.sf.json.JSONObject;

@Service("contactsService")
public class ContactsServiceImpl implements ContactsService {
	
	@Autowired private ContactsRepository contactsRepository;
	@Autowired private GuestRepository guestRepository;
	@Autowired private SubEventRepository subEventRepository;

	@Override
	@Transactional
	public Contacts createContactsByVO(String r, String type, ContactsVO vo, SiteChild child) {

		String phone = vo.getTel1();
		phone = phone.replaceAll("[^0-9\\+]*", "");
		phone = phone.substring(0, 3) + "-" + phone.substring(3);
		phone = phone.substring(0, phone.length() - 4) + "-" + phone.substring(phone.length() - 4);
		
		Contacts result = new Contacts();
		result.setSiteId(child.getId());
		result.setType(type);
		result.setReferer(r == null ? "" : r);
		result.setName(vo.getName());
		result.setGender(vo.getGender());
		result.setPhone(phone);
		result.setEmail(vo.getEmail());
		result.setRequest(vo.getRequest());
		result.setMarryAt(vo.getMarryAtByDate());
		result.setMarriable(vo.getMarriable());
		result.setContent(vo.getContent());
		result.setEnabled(true);
		
		if (vo.getGender() == null || vo.getGender().isEmpty()) vo.setGender("F");
		
		Guest guest = guestRepository.findByMtelOrFtel(phone.replaceAll("[^0-9\\+]*", ""));
		if (guest == null) {
			guest = new Guest();
			guest.setFrom("CONTACT");
			guest.setMode(vo.getGender());
			guest.setArea(child.getArea());
			guest.setStatus(0);
			if (vo.getGender() == null || vo.getGender().equals("F")) {
				guest.setFname(vo.getName());
				guest.setFtel(vo.getPhone().replaceAll("[^0-9]*", ""));
				guest.setFmail(vo.getEmail());
			}
			else {
				guest.setMname(vo.getName());
				guest.setMtel(vo.getPhone().replaceAll("[^0-9]*", ""));
				guest.setMmail(vo.getEmail());
			}
			guest.setMarryDate(vo.getMarryAtByDate());
			guest.setEnabled(true);
			guest.setCreatedAt(new Date());
			guestRepository.save(guest);
			
		}
		result.setArea(child.getArea());
		result.setGuest(guest);
		contactsRepository.save(result);
		
		phone = guest.getMtel();
		SubEvent subevent = null;
		
		if(phone != null && !phone.isEmpty()) {
			subevent = subEventRepository.findTop1ByPhoneNumber(phone.replaceAll("[^0-9\\+]*", ""));			
		}
		if(subevent == null) {
			phone = guest.getFtel();
			if(phone != null && !phone.isEmpty()) {
				subevent = subEventRepository.findTop1ByPhoneNumber(phone.replaceAll("[^0-9\\+]*", ""));			
			}			
		}
		
		if(subevent != null) {
			if(subevent.getAccount() != null) {
				guest.setAccount(subevent.getAccount());
				guest.setStatus(16);
				guestRepository.save(guest);
				subevent.setExUserWhether(1);
				subevent.setGuestId(guest.getId());
				subEventRepository.save(subevent);
			}
		}	
		
		return result;
		
		
	}
	
	@Override
	@Transactional
	public Contacts createContactsByVOAndDismiss(String r, String type, ContactsVO vo, SiteChild child) {
		
		String phone = "";
		String name = "";
		if(child.getGalleries() != null) {
			JSONObject ob = JSONObject.fromObject(child.getGalleries());
			phone = ob.getString("tel");
			name = ob.getString("name");
		}
		phone = phone.replaceAll("[^0-9\\+]*", "");
		phone = phone.substring(0, 3) + "-" + phone.substring(3);
		phone = phone.substring(0, phone.length() - 4) + "-" + phone.substring(phone.length() - 4);
		Guest guest = guestRepository.findByMtelOrFtel(phone.replaceAll("[^0-9\\+]*", ""));
		
		if(guest.getFtel().equals(phone.replaceAll("[^0-9\\+]*", ""))) {
			vo.setGender("F");
		}
		if(guest.getMtel().equals(phone.replaceAll("[^0-9\\+]*", ""))) {
			vo.setGender("M");
		}
		if (vo.getGender() == null || vo.getGender().isEmpty()) vo.setGender("F");
		
		Contacts result = null;
		if(guest != null) {
			
			result = new Contacts();
			result.setSiteId(child.getId());
			result.setGuest(guest);
			result.setArea(child.getArea());
			result.setType(type);
			result.setReferer(r == null ? "" : r);
			result.setName(name);
			result.setGender(vo.getGender());
			result.setPhone(phone);
			result.setRequest("K");
			result.setContent(vo.getContent());
			result.setEnabled(true);
	
			contactsRepository.save(result);
		
		}
		
		return result;
		
		
	}

	@Transactional
	@Override
	public SubEvent createSubEventByVO(ContactsVO vo, SiteChild child,CookieHelper cookie) {
		
		
		String phone = vo.getPhone().replaceAll("[^0-9]", "");
		
		SubEvent result = new SubEvent();
		result.setMarryAt(vo.getMarryAtByDate());
		result.setPhoneNumber(phone);
		result.setUsername(vo.getName());
		result.setCreatedAt(new Date());
		result.setAttendance(true);
		result.setReferer(child.getHost());
		result.setHopeDate(vo.getHopeDate());
		
		Guest guest = guestRepository.findByMtelOrFtel(phone);
		if(guest != null) {
			if(guest.getAccount() != null) {
				result.setAccount(guest.getAccount());
				result.setExUserWhether(1);
				try {						
/*					String str = "박람회 고객이 배정되었습니다.\n" + result.getUsername() + " / " + result.getPhoneNumber();
					String param = "userid=sinbuya&key=0lcz8qsze04yhqsc23djs0jhigrow25b&sender=0234446061&receiver=" + result.getAccount().getPhone() + "&msg=" + URLEncoder.encode(str, "UTF-8");
					HttpClient.post("https://apis.aligo.in/", param).getData();		*/				
				}catch (Exception e) {
					e.printStackTrace();
				}				
			}else {
				result.setExUserWhether(0);
			}
			result.setGuestId(guest.getId());
			
		}else {
			result.setExUserWhether(0);
		}
		
		String referer = cookie.getCookie("r");
		if (referer == null || referer.isEmpty()) referer = "DIRECT";
		
		result.setRefererUri(referer);
		if(vo.getMktCookie() != null && !vo.getMktCookie().isEmpty()) {
			result.setRefererCookie(vo.getMktCookie());	
		}
		
		subEventRepository.save(result);
		
		return result;
	}	
	

}
