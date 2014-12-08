package org.alma.gl

import scala.collection.mutable.ListBuffer
/**
 * @author 
 */
class Gardien {
  
  var listMemento : ListBuffer[Memento]= new ListBuffer[Memento]
  //private var lastCmdUndo : Memento = null
  private var lastCmdUndo : ListBuffer[Memento]= new ListBuffer[Memento]
  var isUndo : Boolean = false
  var isRedo : Boolean = false
  
  def addMemento(m : Memento) : Unit={
    if(isUndo){
      isUndo = false
    }else if(isRedo){
      isRedo = false
    }
    else{
    if(listMemento.length !=0){
      if(!m.equals(listMemento(listMemento.length -1))){
        listMemento += m
      }
    }else{
      listMemento += m
    }
    //affichage
   /* for(x <- 0 to (listMemento.length -1)){
      if(listMemento(x).getBufferApres!=null){println(" id: "+x+" memap:" + listMemento(x).getBufferApres.getZone) }
      else {println(" id: "+x+" memap: null" )}
    }*/
    }
  }
  
  def goToPreviousMemento() : Memento={
   // listMemento.remove(listMemento.length-1)
    var mem : Memento = null
    if(canPrevious){
     // lastCmdUndo = getMemento(listMemento.length-1)
      lastCmdUndo += getMemento(listMemento.length-1)
      listMemento.remove(listMemento.length-1)
      mem = getMemento(listMemento.length-1)
      isUndo = true
    }
    return mem
  }
  
  def redoLastCmd() : Memento={
  //  listMemento.remove(listMemento.length-1)
    var mem : Memento =null
    if(canRedo){
     // mem = lastCmdUndo
      mem = lastCmdUndo(lastCmdUndo.length - 1)
    //  listMemento += lastCmdUndo
      listMemento += lastCmdUndo(lastCmdUndo.length - 1)
    //  lastCmdUndo = null
      lastCmdUndo.remove(lastCmdUndo.length - 1)
      isRedo = true
    }  
    return mem
  }
  
  def canRedo() : Boolean={
    return (/*lastCmdUndo != null*/ lastCmdUndo.length >0)
  }
  
  def canPrevious() : Boolean ={
    return (listMemento.length >1)
  }
  
  def getMemento( index : Int): Memento={
    //println("index:" + index)
    //println("length:" + listMemento.length)
    var indexM : Int = 0

    if((index <=(listMemento.length-1))&&(index>0)){ 
      indexM = index
    }else{
      indexM =  listMemento.length-1
    }  
     //println("indexMem:" + indexMemento)
    return listMemento(indexM)
  }
}