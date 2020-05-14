package com.demo.truck.common;

import java.util.Iterator;
import java.util.List;

public class MyNode<T>  {
	private T element;

	private MyNode<T> parent;

	private List<MyNode<T>> childList;
	

	public MyNode(T element) {
		this.element = element;
	}

	public MyNode<T> getParent() {
		return parent;
	}

	public MyNode(MyNode<T> parent,T element) {
		this.element =element;
		this.parent = parent;
		
	}

	public void setParent(MyNode<T> parent) {
		this.parent = parent;
	}



	public List<MyNode<T>> getChildList() {
		return childList;
	}

	public void setChildList(List<MyNode<T>> childList) {
		this.childList = childList;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.element.toString();
	}
}
