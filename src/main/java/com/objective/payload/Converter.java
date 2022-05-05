package com.objective.payload;

import com.objective.models.Activity;
import com.objective.payload.request.ActivityRequest;
import com.objective.payload.response.ActivityResponse;
import com.objective.payload.response.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;

@Log4j2
public class Converter {

    public static ActivityResponse toPayload(Activity activity) {
        ActivityResponse payload = new ActivityResponse();
        BeanUtils.copyProperties(activity, payload);
        return payload;
    }

    public static Activity fromPayload(ActivityRequest activityRequest) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityRequest, activity);
        return activity;
    }

    public static User toPayload(com.objective.models.User user) {
        User payload = new User();
        BeanUtils.copyProperties(user, payload);
        return payload;
    }
}
