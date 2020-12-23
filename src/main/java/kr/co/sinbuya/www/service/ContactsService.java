package kr.co.sinbuya.www.service;

import kr.co.sinbuya.common.CookieHelper;
import kr.co.sinbuya.entity.Contacts;
import kr.co.sinbuya.entity.SiteChild;
import kr.co.sinbuya.entity.SubEvent;
import kr.co.sinbuya.www.vo.ContactsVO;

public interface ContactsService {

	Contacts createContactsByVO(String referer, String type, ContactsVO vo, SiteChild child);

	Contacts createContactsByVOAndDismiss(String r, String type, ContactsVO vo, SiteChild child);

	SubEvent createSubEventByVO(ContactsVO vo, SiteChild child, CookieHelper cookie);

}
