// JavaScript Document
	 auto();
	  var x =0;
		 function siguiente(suiche){
			 if (x==0)
			   {	      
			     document.getElementById('noticia1').style.display ="none";
	             document.getElementById('noticia2').style.display ="block";
                 document.getElementById('noticia3').style.display ="none"; 
			     x=1;
			   }else if (x==1)
			       {
				     document.getElementById('noticia1').style.display ="none";
	                 document.getElementById('noticia2').style.display ="none";
                     document.getElementById('noticia3').style.display ="block";
				     x=2;	   
				   } else if (x==2) 
			          {
			             document.getElementById('noticia1').style.display ="block";
	                     document.getElementById('noticia2').style.display ="none";
                         document.getElementById('noticia3').style.display ="none";    
			             x=0;
			          }
			if (suiche==true)
			  tiempo=window.setTimeout('auto()',5000);
			 				  
		 }
	     function anterior(){
			 if (x==0)
			   {	      
			     document.getElementById('noticia1').style.display ="none";
	             document.getElementById('noticia2').style.display ="none";
                 document.getElementById('noticia3').style.display ="block"; 
			     x=1;
			   }else if (x==1)
			       {
				     document.getElementById('noticia1').style.display ="block";
	                 document.getElementById('noticia2').style.display ="none";
                     document.getElementById('noticia3').style.display ="none";
				     x=2;	   
				   } else if (x==2) 
			          {
			             document.getElementById('noticia1').style.display ="none";
	                     document.getElementById('noticia2').style.display ="block";
                         document.getElementById('noticia3').style.display ="none";    
			             x=0;
			          }
		 }
        
		function auto() 
		  {
		    siguiente(true);
		  }		