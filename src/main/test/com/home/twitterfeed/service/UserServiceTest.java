package com.home.twitterfeed.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.home.twitterfeed.model.User;
import com.home.twitterfeed.service.UserService;
import com.home.twitterfeed.validate.impl.UserFileValidator;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest extends TestCase {
	
	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

	@InjectMocks
	private UserService userService;
	
	@Mock
	UserFileValidator validator;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void clear() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetUsersFromFileLineWhenLineIsValid() {
		try {
			// IF
			String mockedUserLine = "Alan follows Martin";
			User mockedUser1 = new User();
			mockedUser1.setName("Alan");
			
			User mockedUser2 = new User();
			mockedUser2.setName("Martin");			
			
			List<User> mockedUserList = new ArrayList<User>();
			mockedUserList.add(mockedUser1);			
			mockedUserList.add(mockedUser2);	
			
			when(validator.isFileLineValid(mockedUserLine)).thenReturn(true);

			// WHEN
			List<User> result = userService.getUsers(mockedUserLine);

			// THEN
			assertEquals(mockedUserList.size(), result.size());
			assertEquals(mockedUserList.get(0).getName(), result.get(0).getName());
			assertEquals(mockedUserList.get(1).getName(), result.get(1).getName());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testGetUsersFromFileLineWhenMultpleFollowingLineIsValid() {
		try {
			// IF
			String mockedUserLine = "Alan follows Martin, Ward";
			User mockedUser1 = new User();
			mockedUser1.setName("Alan");
			
			User mockedUser2 = new User();
			mockedUser2.setName("Martin");			
			
			User mockedUser3 = new User();
			mockedUser3.setName("Ward");			
			
			List<User> mockedUserList = new ArrayList<User>();
			mockedUserList.add(mockedUser1);			
			mockedUserList.add(mockedUser2);			
			mockedUserList.add(mockedUser3);		
			
			when(validator.isFileLineValid(mockedUserLine)).thenReturn(true);
			
			// WHEN
			List<User> result = userService.getUsers(mockedUserLine);
			
			// THEN
			assertEquals(mockedUserList.size(), result.size());
			assertEquals(mockedUserList.get(0).getName(), result.get(0).getName());
			assertEquals(mockedUserList.get(1).getName(), result.get(1).getName());
			assertEquals(mockedUserList.get(2).getName(), result.get(2).getName());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testGetFollowingForFollowingOneUser() {
		try {
			// IF
			String mockedUserLine = "Alan follows Martin";
			
			Set<String> following = new HashSet<String>();
			following.add("Martin");
			
			User mockedUser = new User();
			mockedUser.setName("Alan");
			mockedUser.setFollowing(following);

			List<User> mockedUserList = new ArrayList<User>();
			mockedUserList.add(mockedUser);		
			
			when(validator.isFileLineValid(mockedUserLine)).thenReturn(true);
			
			// WHEN
			List<User> result = userService.getUsers(mockedUserLine);
			
			// THEN
			assertEquals(mockedUserList.get(0).getFollowing().size(), result.get(0).getFollowing().size());
			assertEquals(mockedUserList.get(0).getFollowing().iterator().next(), 
					result.get(0).getFollowing().iterator().next());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testGetFollowingForFollowingMultipleUser() {
		try {
			// IF
			String mockedUserLine = "Alan follows Martin, Ward, Sipho";
			
			Set<String> following = new HashSet<String>();
			following.add("Martin");
			following.add("Ward");
			following.add("Sipho");
			
			User mockedUser = new User();
			mockedUser.setName("Alan");
			mockedUser.setFollowing(following);
			
			List<User> mockedUserList = new ArrayList<User>();
			mockedUserList.add(mockedUser);		
			
			when(validator.isFileLineValid(mockedUserLine)).thenReturn(true);
			
			// WHEN
			List<User> result = userService.getUsers(mockedUserLine);
			
			// THEN
			assertEquals(following.size(), result.get(0).getFollowing().size());		
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testGetFollowersForHavingOneFollower() {
		try {
			// IF
			String mockedUserLine = "Alan follows Martin";
			
			Set<String> follower = new HashSet<String>();
			follower.add("Alan");
			
			User mockedUser = new User();
			mockedUser.setName("Martin");
			mockedUser.setFollowing(follower);
			
			List<User> mockedUserList = new ArrayList<User>();
			mockedUserList.add(mockedUser);		
			
			when(validator.isFileLineValid(mockedUserLine)).thenReturn(true);
			
			// WHEN
			List<User> result = userService.getUsers(mockedUserLine);
			
			// THEN
			assertEquals(1, result.get(1).getFollowers().size());
			assertEquals("Martin", result.get(1).getName());
			assertEquals("Alan", result.get(1).getFollowers().iterator().next());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}
	
	@Test
	public void testGetFollowersForHavingMultipleFollower() {
		try {
			// IF
			String mockedUserLine = "Alan follows Martin, Ward, Sipho";
			
			Set<String> following = new HashSet<String>();
			following.add("Martin");
			following.add("Ward");
			following.add("Sipho");
			
			User mockedUser = new User();
			mockedUser.setName("Alan");
			mockedUser.setFollowing(following);
			
			List<User> mockedUserList = new ArrayList<User>();
			mockedUserList.add(mockedUser);		
			
			when(validator.isFileLineValid(mockedUserLine)).thenReturn(true);
			
			// WHEN
			List<User> result = userService.getUsers(mockedUserLine);
			
			// THEN
			assertEquals(1, result.get(1).getFollowers().size());
			assertEquals("Martin", result.get(1).getName());
			assertEquals("Alan", result.get(1).getFollowers().iterator().next());

			assertEquals("Ward", result.get(2).getName());
			assertEquals("Alan", result.get(2).getFollowers().iterator().next());

			assertEquals("Sipho", result.get(3).getName());
			assertEquals("Alan", result.get(3).getFollowers().iterator().next());
			//assertEquals("Alan", result.get(1).getFollowers().iterator().next());		
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}

}
