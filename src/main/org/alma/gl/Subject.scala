package org.alma.gl

abstract class Subject {
  
  private var observers: List[Observer] = Nil
  
  def addObserver(obs: Observer) : Unit={
    observers = obs :: observers
  }

  def notifyObservers() : Unit={
    observers.foreach(_.receiveUpdate(this))
  } 
  
  def affiche() : Unit={}
}