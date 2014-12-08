package org.alma.gl

/**
 * Classe abstraite des commandes
 */
abstract class Command (w : Workspace){
  var work : Workspace = w
  def execute() :Unit={}
}