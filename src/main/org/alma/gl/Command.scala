package org.alma.gl



/**
 * @author E120404Z
 */
abstract class Command (w : Workspace){
  var work : Workspace = w
  def execute() :Unit={}
}