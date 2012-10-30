package com.tempodb.bolt

import java.util.Map

import backtype.storm.task.{OutputCollector, TopologyContext}
import backtype.storm.topology.OutputFieldsDeclarer
import backtype.storm.topology.base.BaseRichBolt
import backtype.storm.tuple.{Fields, Tuple, Values}


class ExclamationBolt extends BaseRichBolt {
  private var collector: OutputCollector = _

  override def prepare(conf: Map[_, _], context: TopologyContext, collector: OutputCollector) {
    this.collector = collector
  }

  override def execute(tuple: Tuple) {
    this.collector.emit(tuple, new Values(tuple.getString(0) + "!!!"))
    this.collector.ack(tuple)
  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer) {
    declarer.declare(new Fields("word"))
  }
}
