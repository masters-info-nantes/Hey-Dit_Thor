package org.alma.gl
/**
 * classe abstraite d'observateur
 */
abstract class Observer( s : Subject) {
  var sujet : Subject = s
  def receiveUpdate( s:Subject) : Unit={}
}