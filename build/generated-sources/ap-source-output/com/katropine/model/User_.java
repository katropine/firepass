package com.katropine.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-04-21T14:12:33")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, Date> created;
    public static volatile SingularAttribute<User, String> lastname;
    public static volatile SingularAttribute<User, String> firstname;
    public static volatile SingularAttribute<User, String> password;

}