"use strict";
var customer = customer || {};
customer = (()=>{
	const WHEN_ERR = '호출하는 JS파일을 찾지 못했습니다.'
	let _, js, css, img, brd_js, brd_vue_js
	let init =()=> {
		_=$.ctx()
		js=$.js()
		css=$.css()
		img=$.img()
		brd_vue_js = js+'/vue/brd_vue.js'
		brd_js = js+'/brd/brd.js'
	}

	let onCreate = ()=>{
		init();
		$.when(
			$.getScript(brd_vue_js),
			$.getScript(brd_js)
		)
		.done(()=>{
			brd_move()	
			})		
			
	}
	
	let brd_move =()=>{
		
		$('#btn_faqView').click(e=>{
			e.preventDefault()
			$('#body_main').empty()
			brd.toggleList({img:$.img()})
		})
		
		$('#btn_inquiryView').click(e=>{
			e.preventDefault()
			$('#body_main').empty()
			$(brd_vue.oneToOne_form({img:$.img()}))
			.appendTo('#body_main')
			
			$('<button/>')
			.appendTo('#brd_vue_btn')
			.css({border: "solid 1px #ddd", width: "137px", "font-size": "13px"})
			.addClass("btn btn-default")
			.text("접수하기")
			.click(e=>{
				
			})
		})
		
		$('#btn_customerView').click(e=>{
			e.preventDefault()
			brd.onCreate(3)
		})
		
		$('#btn_noticeView').click(e=>{
			e.preventDefault()
			$('#body_main').empty()
			brd.onCreate(4)
		})
	}
	
	return {onCreate}
})();