package kr.co.softsoldesk.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.softsoldesk.beans.ContentBean;
import kr.co.softsoldesk.beans.PageBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.service.BoardService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	
	@Resource(name = "loginUserBean")
	UserBean loginUserBean;

	@Autowired
	BoardService boardService;
	
	@GetMapping("/main")
	private String main(@RequestParam("board_info_idx")int board_info_idx, Model model,
			@RequestParam(value = "page", defaultValue = "1")int page) {
	
		
		String boardInfoName = boardService.getBoardInfoName(board_info_idx);//ｰﾔｽﾃﾆﾇ ﾀﾌｸｧ
		List<ContentBean> contentList = boardService.getContentList(board_info_idx, page);//ｰﾔｽﾃﾆﾇ ｰﾔｽﾃｱﾛｵ�
		
		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("board_info_name", boardInfoName);
		model.addAttribute("contentList", contentList);
		
		PageBean pageBean = boardService.getContentCnt(board_info_idx, page);
		model.addAttribute("pageBean", pageBean);
		
		return "board/main";
	}
	
	@GetMapping("/read")
	private String read(@RequestParam("content_idx") int content_idx,
						@RequestParam("board_info_idx") int board_info_idx, Model model) {
						
		ContentBean readContentBean = boardService.getContentInfo(content_idx);
		model.addAttribute("content", readContentBean);

		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("content_idx", content_idx);
		model.addAttribute("loginUserBean", loginUserBean);
		
		return "board/read";
	}
	
	@GetMapping("/write")
	private String write(@ModelAttribute("writeContentBean") ContentBean writeContentBean,
						 @RequestParam("board_info_idx")int board_info_idx) {
		/*ModelAttribute는 "writeContentBean이라는 빈 깡통 객체를 만들게되고,
		@RequestParam은 빈 깡통 상태인 "writeContentBean" 객체에 
		writeContentBean.setContent_board_idx(board_info_idx);
		board_info_idx의 값을 주입해주고 return "board/write"로 넘겨준다.*/
	
		writeContentBean.setContent_board_idx(board_info_idx);
		
		return "board/write";
	}
	
	@PostMapping("/write_pro") //method = "post"니까 PostMapping으로
	private String write_pro(@Valid @ModelAttribute("writeContentBean") ContentBean writeContentBean,
								BindingResult result) {
		
		if(result.hasErrors()) {
			return "board/write";
		} //에러가 발생하면 return "board/write"로 보내버리고
		boardService.addContentInfo(writeContentBean);
		//통과하면 boardService.addConetentInfo(writeContentBean); 는 boardService의 addConetentInfo메소드로 (writeContentBean) 매개변수를 가지고 보낸다.
		return "board/write_success";
	}

	
	@GetMapping("/modify")
	   private String modify(@RequestParam("content_idx") int content_idx,Model model) {
	         
			   ContentBean modifyContentBean = boardService.getContentInfo(content_idx);
			   System.out.println(modifyContentBean.getContent_idx());
			   model.addAttribute("modifyContentBean", modifyContentBean);
	      
	      return "board/modify";
	   }
	
	@PostMapping("modify_pro")
	private String modify_pro(@Valid @ModelAttribute("modifyContentBean") ContentBean modifyContentBean,
								BindingResult result) {
		if (result.hasErrors()) {
			return "board/modify";
		}
		
		boardService.modifyContentBean(modifyContentBean);
		
		return "board/modify_success";
	}
    
	@GetMapping("/not_writer")
	private String not_writer() {
		
		return "board/not_writer";
	}
    
	
	@GetMapping("/delete")
	private String delete(@RequestParam("content_idx")int content_idx) {
		
		boardService.deleteContentInfo(content_idx);
		
		return "board/delete";
	}
	
}
