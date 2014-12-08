package org.alma.gl
/**
 * Classe de selection , une selection a un debut , une fin 
 * (presentes par un curseur double) et un contenu
 */
class Selection{
  private var curseur : DoubleCursor = new DoubleCursor(0,0)
  private var contenu : String = ""
  
  def setContenu(s :String) : Unit={
    contenu = s
  }
  
  def getContenu() : String={
    contenu 
  }
  
  def getCurseur() : DoubleCursor={
    curseur
  }
    
  def setCurseur( cur : DoubleCursor) : Unit={
    curseur = cur
  }
  
  def isEmpty() : Boolean={
    return((curseur.getPosDeb == curseur.getPosFin) && (contenu==""))
  }
}