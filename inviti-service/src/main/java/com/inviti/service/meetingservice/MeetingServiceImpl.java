package com.inviti.service.meetingservice;

import com.inviti.model.domainmodel.Meeting;
import com.inviti.repository.graph.State.MeetingStateRepository;
import com.inviti.repository.graph.identity.MeetingIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vladyslavprytula on 8/27/14.
 */
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    MeetingStateRepository meetingStateRepository;
    @Autowired
    MeetingIdentityRepository meetingIdentityRepository;

    @Override
    @Transactional
    public void save(Meeting meeting) {
    }
}
