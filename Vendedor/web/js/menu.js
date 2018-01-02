// JavaScript Document

function buscar() 
   {
	   if (document.getElementById('menuPrincipal').style.display=='block')
	    {
		  document.getElementById('panelBusqueda').style.display = 'block';
		  document.getElementById('menuPrincipal').style.display = 'none';
		  
	    }else if (document.getElementById('panelBusqueda').style.display=='block')
		  {
			  document.getElementById('menuPrincipal').style.display = 'block';		
			   document.getElementById('panelBusqueda').style.display = 'none';
		  }
       
   } 
 