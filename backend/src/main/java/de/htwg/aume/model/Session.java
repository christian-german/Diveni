package de.htwg.aume.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.val;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@Document("sessions")
public class Session {

	@Id
	private final UUID sessionID;

	private final UUID adminID;

	private final UUID membersID;

	private final List<Member> members;

	public Session copyWith(UUID sessionID, UUID adminID, UUID membersID, List<Member> members) {
		var tmpSession = this;
		if (sessionID != null) {
			tmpSession = new Session(sessionID, tmpSession.adminID, tmpSession.membersID, tmpSession.members);
		}
		if (adminID != null) {
			tmpSession = new Session(tmpSession.sessionID, adminID, tmpSession.membersID, tmpSession.members);
		}
		if (membersID != null) {
			tmpSession = new Session(tmpSession.sessionID, tmpSession.adminID, membersID, tmpSession.members);
		}
		if (members != null) {
			tmpSession = new Session(tmpSession.sessionID, tmpSession.adminID, tmpSession.membersID, members);
		}
		return tmpSession;
	}

	public Session updateEstimation(UUID memberID, int vote) {
		val updatedMembers = members.stream()
			.map(m -> m.getMemberID().equals(memberID) ? m.updateEstimation(vote) : m)
			.collect(Collectors.toList());
		return this.copyWith(null, null, null, updatedMembers);
	}

	public Session resetEstimations() {
		val updatedMembers = members.stream()
			.map(m -> m.resetEstimation())
			.collect(Collectors.toList());
		return this.copyWith(null, null, null, updatedMembers);
	}

}
