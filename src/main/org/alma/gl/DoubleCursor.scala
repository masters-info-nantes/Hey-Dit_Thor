package org.alma.gl
/**
 * Curseur a deux poisitions
 */
class DoubleCursor(private var posDeb : Int, private var posFin : Int) {
  
  def Select( deb : Int, fin : Int) : Unit={
    posDeb = deb
    posFin = fin
  }

  def getPosDeb() : Int={
    return posDeb
  }
  
  def setPosDeb( i :Int) : Unit={
    posDeb = i
  }
  
  def getPosFin() : Int={
    return posFin
  }
  
  def setPosFin( i :Int) : Unit={
    posFin = i
  }
}