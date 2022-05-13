package member.service;

import member.dto.Member;

public interface MemberService {
	Member findByMemberId(String memberId);
}
