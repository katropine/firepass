package com.katropine.model;

import com.katropine.model.ResourceGroup;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-04-21T14:12:33")
@StaticMetamodel(Resource.class)
public class Resource_ { 

    public static volatile SingularAttribute<Resource, Integer> id;
    public static volatile SingularAttribute<Resource, String> body;
    public static volatile SingularAttribute<Resource, String> title;
    public static volatile SingularAttribute<Resource, ResourceGroup> group;

}