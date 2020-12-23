package kr.co.sinbuya.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@SuppressWarnings("serial")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "other_log")
public class OtherLog implements Serializable {

	private PK id;
	private String referer;
	private long createdAt;
	private String userAgent;

	@EmbeddedId
	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}
	
	@Column(name = "user_agent")
	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	@Column(name = "referer")
	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	@Column(name = "created_at")
	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	@Embeddable
	public static class PK implements Serializable {
		private String ip;
		private String sessionId;
		private String type;
		private long typeId;

		public PK() {
			super();
		}

		public PK(String type, long typeId, String ip, String sessionId) {
			super();
			this.ip = ip;
			this.sessionId = sessionId;
			this.type = type;
			this.typeId = typeId;
		}
		
		@Column(name = "type")
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		
		@Column(name = "type_id")
		public long getTypeId() {
			return typeId;
		}

		public void setTypeId(long typeId) {
			this.typeId = typeId;
		}

		@Column(name = "ip")
		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		@Column(name = "session_id")
		public String getSessionId() {
			return sessionId;
		}

		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((ip == null) ? 0 : ip.hashCode());
			result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
			result = prime * result + ((type == null) ? 0 : type.hashCode());
			result = prime * result + (int) (typeId ^ (typeId >>> 32));
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PK other = (PK) obj;
			if (ip == null) {
				if (other.ip != null)
					return false;
			} else if (!ip.equals(other.ip))
				return false;
			if (sessionId == null) {
				if (other.sessionId != null)
					return false;
			} else if (!sessionId.equals(other.sessionId))
				return false;
			if (type == null) {
				if (other.type != null)
					return false;
			} else if (!type.equals(other.type))
				return false;
			if (typeId != other.typeId)
				return false;
			return true;
		}

		
	}

}
