package com.example.resources;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.example.core.Saying;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class HelloWorldResource implements ValueEventListener{

    private final String     template;
    private       String     defaultName;
    private final AtomicLong counter;
    private final Firebase   firebase;

    final Logger logger = LoggerFactory.getLogger(HelloWorldResource.class);

    @Inject
    public HelloWorldResource(@Named("template") String template, @Named("defaultName") String defaultName,
                                     @Named("firebase") Firebase firebase) {
        logger.info("Creating a new HelloWorldResource!");
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
        this.firebase = firebase;
        firebase.child("Saying").addValueEventListener(this);
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        Long counter = this.counter.incrementAndGet();
        logger.info("Saying Hello " + name.or("None..."));
        if (name.isPresent()) {
            firebase.child("Saying").setValue(name);
        }
        final String value = String.format(template, name.or(defaultName));
        return new Saying(counter, value);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        defaultName = (String) dataSnapshot.getValue();
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }
}