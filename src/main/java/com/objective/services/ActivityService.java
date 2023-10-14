package com.objective.services;

import com.objective.models.Activity;
import com.objective.payload.Converter;
import com.objective.payload.request.ActivityRequest;
import com.objective.payload.response.ActivityResponse;
import com.objective.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public ActivityResponse create(ActivityRequest activityRequest) {
        Activity activity = Converter.fromPayload(activityRequest);
        Activity saved = activityRepository.save(activity);
        return Converter.toPayload(saved);
    }

    public ActivityResponse get(String id) {
        Activity activity = activityRepository.findById(id).get();
        return Converter.toPayload(activity);
    }

    public ActivityResponse activity(String name) {
        Activity activity = activityRepository.findByActivity(name);
        return Converter.toPayload(activity);
    }

}
