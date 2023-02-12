package com.movie.app.model;

//import java.util.List;

//import com.movie.app.model.Employee;
//import com.movie.app.model.User;

public class UserSession {
  private User user;
 
  
  public UserSession() {}
  
  public UserSession(User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
