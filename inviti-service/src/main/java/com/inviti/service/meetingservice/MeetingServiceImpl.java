package com.inviti.service.meetingservice;

import com.inviti.model.domainmodel.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MeetingServiceImpl implements MeetingService {


    @Override
    @Transactional
    public void save(Meeting meeting) {
    }
}
