package org.alma.gl

class SimpleCursor (private var pos :Int) extends Cursor{
  
  def setPos(position : Int) : Unit={
    pos = position
  }
  
  def getPos() : Int={
    return pos
  }
  
  override def clone() : SimpleCursor ={
    var cur : SimpleCursor = new SimpleCursor(pos)
    return cur
  }
  
  def equals(c:SimpleCursor): Boolean={
     return (c.pos == pos)
  }
}