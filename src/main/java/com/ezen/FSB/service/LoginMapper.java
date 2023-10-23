package com.ezen.FSB.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.FSB.dto.AlarmDTO;
import com.ezen.FSB.dto.BoardDTO;
import com.ezen.FSB.dto.BusinessCouponUserDTO;
import com.ezen.FSB.dto.DontgoDTO;
import com.ezen.FSB.dto.GameDTO;
import com.ezen.FSB.dto.MemberDTO;
import com.ezen.FSB.dto.ProfileDTO;
import com.ezen.FSB.dto.ShopPointHistoryDTO;

@Service
public class LoginMapper {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	public MemberDTO findMember(String id){ // 회원 찾기
        return sqlSession.selectOne("findMember",id);
     }
	
	public MemberDTO findId(String name){ // 이름으로 찾기
        return sqlSession.selectOne("findId", name);
     }
	
	public MemberDTO findIdnum(String name){ // 이름으로 찾기
        return sqlSession.selectOne("findIdnum", name);
     }
	
	public MemberDTO findNick(String name){ // 닉네임 중복 찾기
        return sqlSession.selectOne("findNick", name);
     }
	
	
	public List<MemberDTO> idMember(String id){ // 아이디 일치 확인
        return sqlSession.selectList("idMember",id);
     }
	
	public String loginMember(String id){ // 비밀번호 일치 여부 확인
        return sqlSession.selectOne("loginMember", id);
     }
	
	public String joinMember(String id){ // 승인 여부 확인
        return sqlSession.selectOne("joinMember", id);
     }
	
	 public int changePw(MemberDTO dto) {
		    return sqlSession.update("changePw", dto);
	 }
	 
	 public int changeNick(MemberDTO dto) {
		    return sqlSession.update("changeNick", dto);
	 }
	 
	 public int changePhone(MemberDTO dto) {
		    return sqlSession.update("changePhone", dto);
	 }
	 
	 public int changeMsg(MemberDTO dto) {
		    return sqlSession.update("changeMsg", dto);
	 }
	 
	 public int changeTag(MemberDTO dto) {
		    return sqlSession.update("changeTag", dto);
	 }
	 
	 public int changeBadge(MemberDTO dto) {
		    return sqlSession.update("changeBadge", dto);
	 }
	 
	 public int changeBadgeOk(MemberDTO dto) {
		    return sqlSession.update("changeBadgeOk", dto);
	 }
	 
	 
	 
	 public int plusCount(MemberDTO dto) {
		    return sqlSession.update("plusCount", dto);
	 }
	 
	 public int agreeUpdate(MemberDTO dto) {
		    return sqlSession.update("agreeUpdate", dto);
	 }
	 
	 public int imageUpdate(MemberDTO dto) {
		    return sqlSession.update("imageUpdate", dto);
	 }
	 
	 public int insertMember2(MemberDTO dto) {//오토커밋됨 스프링 이용하면
		 // f_member_seq.nextval 를 한 mem_num 을 가져오기
		 int mem_num = sqlSession.selectOne("basicMemberNextNum");
		 // MemberDTO insert
		 dto.setMem_num(mem_num);
		 int res2 = sqlSession.insert("insertMember2", dto); // 루트에 세션등록해놨으니까 . sql문도 안쓰고 클로즈~ 뭐 어쩌구도 안씀
		 
		 // 프로필 생성 (피드를 위한)
			String[] CHO = { "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ",
					"ㅎ" };
			String[] JOONG = { "ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ",
					"ㅡ", "ㅢ", "ㅣ" };
			String[] JONG = { "", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ",
					"ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ" };

			String nickname = dto.getMem_nickname();
			String nickname_separated = "";

			for (int i = 0; i < nickname.length(); i++) {
				char uniVal = nickname.charAt(i);

				// 한글일 경우만 시작해야 하기 때문에 0xAC00부터 아래의 로직을 실행한다
				if (uniVal >= 0xAC00) {
					uniVal = (char) (uniVal - 0xAC00);

					char cho = (char) (uniVal / 28 / 21);
					char joong = (char) ((uniVal) / 28 % 21);
					char jong = (char) (uniVal % 28);

					nickname_separated += CHO[cho];
					nickname_separated += JOONG[joong];
					nickname_separated += JONG[jong];
				} else {
					nickname_separated += uniVal;
				}
			}
			ProfileDTO dto2 = new ProfileDTO();
			dto2.setMem_num(mem_num);
			dto2.setProf_nickname_separated(nickname_separated);

			
			int res3 = sqlSession.insert("insertDefaultProfile", dto2);
			
			// 포인트 
			String point_type = "+";
			String point_content = "신규가입";
			int point_amount = 3000;
			int point_total = 3000;
			ShopPointHistoryDTO dto3 = new ShopPointHistoryDTO();
			dto3.setMem_num(mem_num);
			dto3.setPoint_type(point_type);
			dto3.setPoint_content(point_content);
			dto3.setPoint_amount(point_amount);
			dto3.setPoint_total(point_total);
			int res4 = sqlSession.insert("memberPoint",dto3);
			if(res4>0) {
				System.out.println("포인트 신규 등록 성공");
			}else {
				System.out.println("포인트 신규 등록 실패");			
			}
			
			if(res2>0 && res3>0 && res4>0) {
				return 1;
			}else {
				return -1;
			}
		}
	 
	 public int insertKakaoMember(MemberDTO dto) {//오토커밋됨 스프링 이용하면
			int res =  sqlSession.insert("insertKakaoMember", dto); // 루트에 세션등록해놨으니까 . sql문도 안쓰고 클로즈~ 뭐 어쩌구도 안씀
			
		// 프로필 생성 (피드를 위한)
		String[] CHO = { "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ",
				"ㅎ" };
		String[] JOONG = { "ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ",
				"ㅡ", "ㅢ", "ㅣ" };
		String[] JONG = { "", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ",
				"ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ" };

		String nickname = dto.getMem_nickname();
		String nickname_separated = "";

		for (int i = 0; i < nickname.length(); i++) {
			char uniVal = nickname.charAt(i);

			// 한글일 경우만 시작해야 하기 때문에 0xAC00부터 아래의 로직을 실행한다
			if (uniVal >= 0xAC00) {
				uniVal = (char) (uniVal - 0xAC00);

				char cho = (char) (uniVal / 28 / 21);
				char joong = (char) ((uniVal) / 28 % 21);
				char jong = (char) (uniVal % 28);

				nickname_separated += CHO[cho];
				nickname_separated += JOONG[joong];
				nickname_separated += JONG[jong];
			} else {
				nickname_separated += uniVal;
			}
		}
		ProfileDTO dto2 = new ProfileDTO();
		dto2.setMem_num(dto.getMem_num());
		dto2.setProf_nickname_separated(nickname_separated);

		int res2 = sqlSession.insert("insertDefaultProfile", dto2);

		if (res2 > 0 && res > 0) {
			return 1;
		} else {
			return -1;
		}
	}
	 
	 //배찌
	 public int memCountLike(int num) {
			int res = sqlSession.selectOne("memCountLike", num);
			return res;
		}
	 
	 public int memCountShop(int num) {
			int res = sqlSession.selectOne("memCountShop", num);
			return res;
		}
	 
	 
	 //보드
	 
		public int memCountBoard(int num) {
			int res = sqlSession.selectOne("memCountBoard", num);
			return res;
		}
		
		public int memCountShBoard(int num) {
			int res = sqlSession.selectOne("memCountShBoard", num);
			return res;
		}
		
		public int memCountFreeBoard(int num) {
			int res = sqlSession.selectOne("memCountFreeBoard", num);
			return res;
		}
		
		public int memCountSecretBoard(int num) {
			int res = sqlSession.selectOne("memCountSecretBoard", num);
			return res;
		}
		
		public int memCountReply(int num) {
			int res = sqlSession.selectOne("memCountReply", num);
			return res;
		}
		
		public  List<BoardDTO> MemfreelistBoard(java.util.Map<String,Integer> params){
			List<BoardDTO> list = sqlSession.selectList("MemfreelistBoard", params);
			
			return list;
			}
		
		public  List<BoardDTO> MemShlistBoard(java.util.Map<String,Integer> params){
			List<BoardDTO> list = sqlSession.selectList("MemShlistBoard", params);
			
			return list;
			}
		
		public  List<BoardDTO> MemSecretlistBoard(java.util.Map<String,Integer> params){
			List<BoardDTO> list = sqlSession.selectList("MemSecretlistBoard", params);
			
			return list;
			}
		
		public  List<BoardDTO> freeReply(java.util.Map<String,Integer> params){
			List<BoardDTO> list = sqlSession.selectList("freeReply", params);
			
			return list;
			}
		
		public  List<BoardDTO> secretReply(java.util.Map<String,Integer> params){
			List<BoardDTO> list = sqlSession.selectList("secretReply", params);
			
			return list;
			}
		
		
		public  List<BoardDTO> shReply(java.util.Map<String,Integer> params){
			List<BoardDTO> list = sqlSession.selectList("shReply", params);
			
			return list;
			}
		
		 // 카카오 멤버 넘버
		 public int kakaoMemNum() {
			 return sqlSession.selectOne("kakaoMemNum");
		 }
		// 닉네임 변경 0719 추가
				 public int updateNicknameProfile(ProfileDTO dto) {
					 return sqlSession.update("updateNicknameProfile", dto);
				 }
		
		 //회원탈퇴 이유 인서트
		 public int reason(DontgoDTO dto) {//오토커밋됨 스프링 이용하면
				return sqlSession.insert("reason", dto); // 루트에 세션등록해놨으니까 . sql문도 안쓰고 클로즈~ 뭐 어쩌구도 안씀
			}
		 
		 public int deleteMem(String id) {
				return sqlSession.delete("deleteMem", id);
			}
		 
		// 멤버 별 좋아요 누른 보드게임 목록
			public List<GameDTO> myPageGameLikeList(int mem_num) {
				return sqlSession.selectList("myPageGameLikeList", mem_num);
			}
			
			// 멤버 별 좋아요 누른 보드게임 목록
			public List<BusinessCouponUserDTO> myPageCouponList(int mem_num) {
				return sqlSession.selectList("myPageCouponList", mem_num);
			}

			//쿠 폰 삭제
			 public int delCoupon(int num) {
					return sqlSession.delete("delCoupon", num);
				}
			 
			 //자동 삭제 기한에 따라
			
				public int expireBcouponUser() {
					return sqlSession.delete("expireBcouponUser");
				}
				
				 //보드 알람 인서
				 public int addBoardAlarm(AlarmDTO dto) {//오토커밋됨 스프링 이용하면
						return sqlSession.insert("addBoardAlarm", dto); // 루트에 세션등록해놨으니까 . sql문도 안쓰고 클로즈~ 뭐 어쩌구도 안씀
					}
				 
					// 멤버별 보드게임 알람 리스트
					public List<AlarmDTO> boardAlarmList(int mem_num) {
						return sqlSession.selectList("boardAlarmList", mem_num);
					}
					
					//<!-- 댓글번호로 게시글 번호 찾기 -->
					public int replyBoardNum(int num){ 
				        return sqlSession.selectOne("replyBoardNum", num);
				     }
					
					//<!-- 글번호로 작성자 번호 찾기 -->
					public int BoardNum(int num){ 
				        return sqlSession.selectOne("BoardNum", num);
				     }
					
					//<!-- 글번호로 제목 찾 -->
					public String BoardTitle(int num){ 
				        return sqlSession.selectOne("BoardTitle", num);
				     }
					
					//<!-- 글번호로 작성자 번호 찾기 -->
					public int shBoardNum(int num){ 
				        return sqlSession.selectOne("shBoardNum", num);
				     }
					
					//<!-- 글번호로 제목 찾 -->
					public String shBoardTitle(int num){ 
				        return sqlSession.selectOne("shBoardTitle", num);
				     }
					
					
					
					//<!-- 글번호로 작성자 번호 찾기 -->
					public int BoardNumReply(int num){ 
				        return sqlSession.selectOne("BoardNumReply", num);
				     }
					
					//<!-- 글번호로 제목 찾 -->
					public int BoardTitleReply(int num){ 
				        return sqlSession.selectOne("BoardTitleReply", num);
				     }
					
					//<!-- 글번호로 작성자 번호 찾기 -->
					public int shBoardNumReply(int num){ 
				        return sqlSession.selectOne("shBoardNumReply", num);
				     }
					
					//<!-- 글번호로 제목 찾 -->
					public int shBoardTitleReply(int num){ 
				        return sqlSession.selectOne("shBoardTitleReply", num);
				     }
					
					// 알람 삭제 
					 public int delAlarm(int num) {
							return sqlSession.delete("delAlarm", num);
						}
					 
					 public int readAlarm(int num) {
						    return sqlSession.update("readAlarm", num);
					 }
					// 멤버별 보드게임 알람 리스트
						public List<AlarmDTO> boardunReadAlarmList(int mem_num) {
							return sqlSession.selectList("boardunReadAlarmList", mem_num);
						}
	/*
	
	
	
	public List<StudentDTO> listStudent(){
		return sqlSession.selectList("listStudent");
	}
	
	
	
	
	*/
}
