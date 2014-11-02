package com.inviti.service.meetingservice;

import com.inviti.model.domainmodel.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vladyslavprytula on 8/27/14.
 */
public class MeetingServiceImpl implements MeetingService {


    @Override
    @Transactional
    public void save(Meeting meeting) {
    }
}
