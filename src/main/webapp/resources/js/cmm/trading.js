"use strict";
var trading = trading || {}
trading = (()=>{
       const WHEN_ERR = '호출하는 JS파일을 찾지 못했습니다.'
       let _, js, css, img, s_trading_vue_js
       let init =()=> {
              _=$.ctx()
              js=$.js()
              css=$.css()
              img=$.img()
              s_trading_vue_js=js+'/vue/s_trading_vue.js'
       }
       
       let onCreate =()=>{
              init()
              $.when(
                     $.getScript(s_trading_vue_js)
              )
              .done(()=>{
					setContentView()
					button_click()
					repeat()
					biz_info()
					
              })
              .fail()
       }
       
       let setContentView =()=>{
              $('#body_main').empty()
              .html(s_trading_vue.trading_mainbody({css: $.css(), img: $.img()}))
              .appendTo('#body_main')
				vuemystock()
				addstock_info()
       }
       
       let button_click=()=>{
              $('#s_btn_maesu').click(e=>{
                     e.preventDefault()
			   msstock()
              })
              $('#s_btn_maedo').click(e=>{
                     e.preventDefault()
			   mdstock()
              })
              $('#s_btn_jungjung').click(e=>{
                     e.preventDefault()
                     $('#s_trading_detailchang').empty()
                     $('#s_trading_detailchang').html(s_trading_vue.trading_jungjungChang({css: $.css(), img: $.img()}))
              })
              $('#s_btn_chuiso').click(e=>{
                     e.preventDefault()
                     $('#s_trading_detailchang').empty()
                     $('#s_trading_detailchang').html(s_trading_vue.trading_chuisoChang({css: $.css(), img: $.img()}))
              })
              $('#btn_search').click(e=>{
                     e.preventDefault()
                     $.ajax({
                           url:sessionStorage.getItem('ctx')+'/tradings/detail/'+$('#input_search').val(),
                           Async: false,
                           type: 'POST',
                           dataType : 'json',
                           data : JSON.stringify({
                                  stockname : $('#input_search').val()
                           }),
                           contentType:'application/json',
                           success: d =>{
                                  if(d.msg === 'success'){
                                         detailstock(d.trading)
                                  }else {
                                  }
                           },
                           error: e =>{
                           }
                     })
		})
		  
              $('#add_stock').click(e=>{
                     e.preventDefault()
                     let cid = sessionStorage.getItem('cid')
                     let stockname = $('#input_search').val()
                     $.ajax({
                            Async: false,
                            type:'POST',
                            url: _+'/tradings/addstock/',
                            dataType: 'json',
                            data: JSON.stringify({
                                   cid: cid,
                                   stockname : stockname
                            }),
                            contentType: 'application/json',
                            success: d => {
                                   if(d.msg === 'success'){
                                	   addstock_info()
                                	   
                                   }else {
                                   }
                            },
                            error: e =>{
                            }
                     })
              })

              $('#del_stock').click(e=>{
                     e.preventDefault()
                     $.getJSON(_+'/tradings/delstock/'+$('#input_search').val(),d=>{
                    	 addstock_info()
                     })
              })
              
              $('#btn_check').click(e=>{
                     e.preventDefault()
                     let temp = $('#ms_nprice').text()
                     let change = temp.replace(',' ,'')
                     let nprice = parseFloat(change)
                     let msnum = Number($('#msnum').val())
                     let mstprice = (nprice * msnum)
                     let lmstprice = mstprice.toString().replace(/(\d)(?=(?:\d{3})+(?!\d))/g, "$1,")
                     let mprice = Number($('#msprice').val())*msnum
                     let buylimits = mprice.toString().replace(/(\d)(?=(?:\d{3})+(?!\d))/g, "$1,")
                     let ordertype = String($('[name="join_invest"]').val())
                     if(ordertype === '1'){
                            $('#mstprice').text(lmstprice)
                     }else if(ordertype === '2'){
                            $('#mstprice').text(buylimits)
                     }
              })
             
              $('#btn_mdcheck').click(e=>{
                     e.preventDefault()
                     
                     let temp = $('#md_nprice').text()
                     let change = temp.replace(',' ,'')
                     let nprice = parseFloat(change)
                     let mdnum = Number($('#mdnum').val())
                     let mdtprice = (nprice * mdnum)
                     let lmdtprice = mdtprice.toString().replace(/(\d)(?=(?:\d{3})+(?!\d))/g, "$1,")
                     let mprice = Number($('#mdprice').val())*mdnum
                     let selllimits = mprice.toString().replace(/(\d)(?=(?:\d{3})+(?!\d))/g, "$1,")
                     let ordertype = String($('[name="join_invest"]').val())
                     if(ordertype === '1'){
                            $('#mdtprice').text(lmdtprice)
                     }else if(ordertype === '2'){
                            $('#mdtprice').text(selllimits)
                     }
              })

              
              $('#s_mscf_btn').click(e=>{
                     e.preventDefault()
                     let cid = sessionStorage.getItem('cid')
                     let ordertype = String($('[name="join_invest"]').val())
                     if(ordertype === '1'){
                            $.ajax({
                                   url:sessionStorage.getItem('ctx')+'/tradings/msmarket/',
                                   Async: false,
                                   type: 'POST',
                                   dataType : 'json',
                                   data : JSON.stringify({
                                          stockcode : $('#msstockcode').text(),
                                          ordernum : $('#msnum').val(),
                                          orderprice : $('#ms_nprice').text().replace(',',''),
                                          ordertype : $('#msmarket').val(),
                                          cid : cid,
                                          stockname : $('#msstockname').text()
                                   }),
                                   contentType:'application/json',
                                   success: d =>{
                                          if(d.msg === 'success'){
                                        	  vuemystock()
                                          }else {
                                          }
                                   },
                                   error: e =>{
                                   }
                            })
                     }else{
                            $.ajax({
                                   url:sessionStorage.getItem('ctx')+'/tradings/mscheck/',
                                   Async: false,
                                   type: 'POST',
                                   dataType : 'json',
                                   data : JSON.stringify({
                                          stockcode : $('#msstockcode').text(),
                                          ordernum : $('#msnum').val(),
                                          orderprice : $('#msprice').val(),
                                          ordertype : $('#msmarket').val(),
                                          cid : sessionStorage.getItem('cid'),
                                          stockname : $('#msstockname').text()
                                   }),
                                   contentType:'application/json',
                                   success: d =>{
                                          if(d.msg === 'success'){
                                          }else {
                                          }
                                   },
                                   error: e =>{
                                   }
                            })
                     }
                     
              })

              $('#s_mdcf_btn').click(e=>{
                     e.preventDefault()
                     let cid = sessionStorage.getItem('cid')
                     let ordertype = String($('[name="join_invest"]').val())
                     let temp = $('#md_nprice').text()
                     let change = temp.replace(',' ,'')
                     if(ordertype === '1'){
                            $.ajax({
                                   url:sessionStorage.getItem('ctx')+'/tradings/mdmarket/',
                                   Async: false,
                                   type: 'POST',
                                   dataType : 'json',
                                   data : JSON.stringify({
                                          cid : cid,
                                          stockcode : $('#mdstockcode').text(),
                                          ordernum : $('#mdnum').val(),
                                          orderprice : change,
                                          ordertype : $('#mdmarket').val(),
                                          stockname : $('#mdstockname').text()
                                   }),
                                   contentType:'application/json',
                                   success: d =>{
                                          if(d.msg === 'success'){
                                        	  vuemystock()
                                          }else if(d.msg === 'check'){
                                          }
                                   },
                                   error: e =>{
                                   }
                            })
                     }else{
                            $.ajax({
                                   url:sessionStorage.getItem('ctx')+'/tradings/mdcheck/',
                                   Async: false,
                                   type: 'POST',
                                   dataType : 'json',
                                   data : JSON.stringify({
                                          cid : cid,
                                          stockcode : $('#mdstockcode').text(),
                                          ordernum : $('#mdnum').val(),
                                          orderprice : $('#mdprice').val(),
                                          ordertype : $('#mdmarket').val(),
                                          stockname : $('#mdstockname').text()
                                   }),
                                   contentType:'application/json',
                                   success: d =>{
                                          if(d.msg === 'success'){
                                          }else {
                                          }
                                   },
                                   error: e =>{
                                   }
                            })
                     }
                     
              })

       }
       
       let vuemystock=()=>{
    	   $.getJSON(_+'/tradings/vuemystock/'+sessionStorage.getItem('cid'),d=>{
    		   $('#sTradeHaveStock tbody').empty()
	    	   $.each(d.mylistAll,(i,j)=>{
	    	    	$(`<tr id="${j.stockcode}">
	    	    		<td>${j.stockname}</td>
	    	    		<td>${j.stockcode}</td>
	    	    		<td>${j.quantitynum}</td>
	    	    		<td>${j.orderprice}</td>
	    	    		<td>${j.conprice}</td>
	    			</tr>`)
	    			.appendTo('#sTradeHaveStock tbody')
	    	   })
	    	   $.each(d.mylistData,(i,j)=>{
		    		   $(`
		    				<td>${j.nprice}</td>
		    	    		<td>${j.cprice}</td>
		    	    		<td>${j.buyprice}</td>
		    	    		<td>${j.sellprice}</td>
		    		   `)
		    		   .appendTo(`#${j.stockcode}`)
	    	   })
    	   })
       }
       
       let detailrefresh=()=>{
              $.ajax({
                     url:sessionStorage.getItem('ctx')+'/tradings/detail/'+$('#input_search').val(),
                     Async: false,
                     type: 'POST',
                     dataType : 'json',
                     data : JSON.stringify({
                            stockname : $('#input_search').val()
                     }),
                     contentType:'application/json',
                     success: d =>{
                            if(d.msg === 'success'){
                                   detailstock(d.trading)
                            }
                     },
                     error: e =>{
                     }
              })
       }
	
      let detailstock =x=>{
		$('#s_trading_detailchang').empty()
		.html(s_trading_vue.trading_detail(x))
		.appendTo('#s_trading_detailchang')
      }

      let msstock =x=>{
		$.getJSON(_+'/tradings/msstock/',d=>{
			$('#s_trading_detailchang').empty()
                     $('#s_trading_detailchang').html(s_trading_vue.trading_msChang(d.trading))
                     $('#msmarket').click(e=>{
                     $('#msmarket').val(1)
                     })
                     $('#mslimits').click(e=>{
                            $('#msmarket').val(2)
                     })
                     button_click()
              })
       }
       
	let mdstock =x=>{
		$.getJSON(_+'/tradings/mdstock/',d=>{
			$('#s_trading_detailchang').empty()
                     $('#s_trading_detailchang').html(s_trading_vue.trading_mdChang(d.trading))
                     $('#mdmarket').click(e=>{
                     $('#mdmarket').val(1)
                     })
                     $('#mdlimits').click(e=>{
                            $('#mdmarket').val(2)
                     })
                     button_click()
		})
       }
       
	let ingcrawl =()=>{
            $.getJSON(sessionStorage.getItem('ctx')+'/tradings/ingcrawl/',d=>{
//                   detailrefresh()
//                   temprefreshms()
//                   temprefreshmd()
            })
       }

       let biz_info =()=>{
            $.getJSON(_+'/tradings/bizinfo/',d=>{
            })
       }

       let temprefreshms =()=>{
              $.ajax({
                     url:sessionStorage.getItem('ctx')+'/tradings/temprefreshms/',
                     Async: false,
                     type: 'POST',
                     dataType : 'json',
                     data : JSON.stringify({
                            cid : sessionStorage.getItem('cid'),
                            tradetype : '매수'
                     }),
                     contentType:'application/json',
                     success: d =>{
                            if(d.msg === 'success'){
                            }else if(d.msg === 'check'){
                            }else{
                            }
                     },
                     error: e =>{
                     }
              })
       }

       let temprefreshmd =()=>{
              $.ajax({
                     url:sessionStorage.getItem('ctx')+'/tradings/temprefreshmd/',
                     Async: false,
                     type: 'POST',
                     dataType : 'json',
                     data : JSON.stringify({
                            cid : sessionStorage.getItem('cid'),
                            tradetype : '매도'
                     }),
                     contentType:'application/json',
                     success: d =>{
                            if(d.msg === 'success'){
                            }else if(d.msg === 'check'){
                            }else{
                            }
                     },
                     error: e =>{
                     }
              })
       }

       let listone =()=>{
              $('#listone').click(e=>{
                     e.preventDefault()
                     $.ajax({
                           url:_+'/tradings/detail/'+$('#listone').text(),
                           Async: false,
                           type: 'POST',
                           dataType : 'json',
                           data : JSON.stringify({
                                  stockname : $('#addone').text()
                           }),
                           contentType:'application/json',
                           success: d =>{
                                  if(d.msg === 'success'){
                                         detailstock(d.trading)
                                  }
                           },
                           error: e =>{
                           }
                     })
              })
       }
       
       let addstock_info =()=>{
    	   $.getJSON(_+'/tradings/addstockinfo/'+sessionStorage.getItem('cid'),d=>{
    		   		$('#AddStock tbody').empty()
                   $.each(d.addlist,(i,j)=>{
                	   $(`
                	   <tr>
                			   <td>${j.stockname}</td>
                			   <td>${j.stockcode}</td>
                			   <td>${j.nprice}</td>
                			   <td>${j.tvolume}</td>
                			   <td>${j.pcontrast}</td>
                			   <td>${j.frate}</td>
                			   <td>${j.hprice}</td>
                			   <td>${j.lprice}</td>
                			   <td>${j.cprice}</td>
                	   </tr>`)
                	   .appendTo('#AddStock tbody')
                   })
    	   })
       }

	let repeat = setInterval(ingcrawl, 60000);

		setTimeout(function(){
			clearInterval(repeat);
		   }, 600000);
       
	return {onCreate}
})();