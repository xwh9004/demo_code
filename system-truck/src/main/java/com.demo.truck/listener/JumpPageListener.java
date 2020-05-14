
package com.demo.truck.listener;

public interface JumpPageListener {
	
	 public void setJumpParam(String fromPage,String toPage,Object param);
	
	 public void afterJump(); 
	
     public void JumpToPage();
     
     public String getFromPageName();
     
     public Object getParam();
}
