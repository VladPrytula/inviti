package com.inviti.service.metricservice;

import com.inviti.model.state.Meeting;
import com.inviti.repository.graph.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by vladyslavprytula on 8/18/14.
 */
@Service
public class ProximityMeetingImpl implements Proximity<Meeting>{

    @Autowired
    private MeetingRepository meetingRepository;

    @Override
    public Set<Meeting> getNearby(Meeting meeting) {
        return meetingRepository.getNearby(meeting);
    }
}
