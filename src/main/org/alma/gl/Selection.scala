package org.alma.gl

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