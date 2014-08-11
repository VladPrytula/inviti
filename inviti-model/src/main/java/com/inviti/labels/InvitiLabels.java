package com.inviti.labels;

import org.neo4j.graphdb.Label;

/**
 * Created by vladyslavprytula on 8/8/14.
 * since we expect to have two types of nodes : users(Invited) and meetings (Meeting) we can define two labels
 * labeling nodes should ease the burden of finding nodes
 */
public enum InvitiLabels implements Label{
    Invited,
    Meeting
}
