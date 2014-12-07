package org.alma.gl

abstract class Observer( s : Subject) {
  var sujet : Subject = s
  def receiveUpdate( s:Subject) : Unit={}
}