package org.alma.gl
/**
 * exemple d'un observateur du workspace
 */
class ObsConcretA(s : Subject) extends Observer(s){
  
  override def receiveUpdate( s:Subject) : Unit={
    sujet = s
    s.affiche
  }
}