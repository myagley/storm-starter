package com.tempodb

import backtype.storm.{Config, LocalCluster, StormSubmitter}
import backtype.storm.topology.TopologyBuilder
import backtype.storm.utils.Utils

import com.tempodb.bolt.ExclamationBolt
import com.tempodb.spout.TestWordSpout


object Main {

  def main(args: Array[String]) {
    val builder = new TopologyBuilder()
    builder.setSpout("word", new TestWordSpout, 10)
    builder.setBolt("exclaim1", new ExclamationBolt, 3)
           .shuffleGrouping("word")
    builder.setBolt("exclaim2", new ExclamationBolt, 2)
           .shuffleGrouping("exclaim1")

    val config = new Config
    config.setDebug(true)

    if(args != null && args.length > 0) {
      config.setNumWorkers(3)
      StormSubmitter.submitTopology(args(0), config, builder.createTopology)
    } else {
      val cluster = new LocalCluster
      cluster.submitTopology("test", config, builder.createTopology)
      Utils.sleep(10000)
      cluster.killTopology("test")
      cluster.shutdown
    }
  }
}
