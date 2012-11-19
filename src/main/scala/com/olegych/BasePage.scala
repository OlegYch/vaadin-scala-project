package com.olegych

import vaadin.scala.CustomComponent
import collection.mutable.ListBuffer

/**
  */
trait BasePage extends CustomComponent with Validations with DelayedInit {
  var params: String = ""
  private lazy val initCode = new ListBuffer[() => Unit]

  override def delayedInit(body: => Unit) {
    initCode += (() => body)
  }

  def init(params: String) = {
    this.params = params
    initCode.foreach(_())
  }
}
