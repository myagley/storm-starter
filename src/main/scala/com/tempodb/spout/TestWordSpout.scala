package com.tempodb.spout

import java.util.Map

import backtype.storm.spout.SpoutOutputCollector
import backtype.storm.task.TopologyContext
import backtype.storm.topology.OutputFieldsDeclarer
import backtype.storm.topology.base.BaseRichSpout
import backtype.storm.tuple.{Fields, Values}
import backtype.storm.utils.Utils


class TestWordSpout(val isDistributed: Boolean) extends BaseRichSpout {

  private var collector: SpoutOutputCollector = _

  def this() = this(true)

  def open(conf: Map[_, _], context: TopologyContext, collector: SpoutOutputCollector) {
    this.collector = collector
  }

  override def close() { }

  def nextTuple() {
    Utils.sleep(100)
    this.collector.emit(new Values("hello"))
  }

  override def ack(msgId: AnyRef) { }

  override def fail(msgId: AnyRef) { }

  def declareOutputFields(declarer: OutputFieldsDeclarer) {
    declarer.declare(new Fields("words"))
  }

  override def getComponentConfiguration(): Map[String, AnyRef] = {
    return null
  }
}
