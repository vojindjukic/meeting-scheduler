package org.vojin.meetingscheduler.specification;

import org.springframework.data.jpa.domain.Specification;
import org.vojin.meetingscheduler.model.Meeting;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class MeetingSpecification implements Specification<Meeting> {

    private Map.Entry<String, Object> criteria;

    public MeetingSpecification(Map.Entry<String, Object> criteria){
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Meeting> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        switch (criteria.getKey()) {
            case "fromDate":
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.get("date"), (LocalDateTime)criteria.getValue());
            case "toDate":
                return criteriaBuilder.lessThanOrEqualTo(
                        root.get("date"), (LocalDateTime)criteria.getValue());
            case "owner":
                return criteriaBuilder.equal(
                        root.get("owner"), criteria.getValue().toString());
        }

        return null;
    }

    public static Specification createSpecification(HashMap<String,Object> params){

        Specification spec = null;

        for (Map.Entry<String, Object> entry : params.entrySet()){
            if (spec == null) spec = new MeetingSpecification(entry);
            else spec = spec.and(new MeetingSpecification(entry));
        }
        return spec;
    }
}