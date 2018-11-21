package pl.message.api.rest.user.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.message.api.rest.exceptions.EmailDuplicateException;
import pl.message.api.rest.exceptions.NotFoundUserException;
import pl.message.api.rest.interfaces.CUDController;
import pl.message.api.rest.user.interfaces.UserController;
import pl.message.api.rest.user.interfaces.UserService;
import pl.message.api.rest.util.MessagesUtil;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserControllerImpl implements CUDController<UserDTO>, UserController {

    private Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);

    @Autowired
    UserService userService;
    @Autowired
    private MessagesUtil messagesUtil;

    @GetMapping
    public ResponseEntity<?> getUsers(){
        try {
            List<UserDTO> users = userService.getAllUsers();
            logger.info(messagesUtil.getMessage("message.all.users"));
            logger.debug(messagesUtil.getMessage("message.request"), users);
            return new ResponseEntity<>(users, HttpStatus.CREATED);
        } catch (NotFoundUserException e) {
            logger.warn(messagesUtil.getMessage("message.not.found.any.users"), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DataAccessException e){
            logger.error(messagesUtil.getMessage("message.database.error"), e);
            return new ResponseEntity<>(messagesUtil.getMessage("message.response.database.error.response"), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    @GetMapping(params = "name")
    public ResponseEntity<?> getUsersByName(@RequestParam(value = "name") String name) {
        try {
            logger.debug(messagesUtil.getMessage("message.name"), name);
            List<UserDTO> users = userService.getByName(name);
            logger.debug(messagesUtil.getMessage("message.response"), users);
            logger.info(messagesUtil.getMessage("message.all.users.with.name"), name);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (NotFoundUserException e) {
            logger.warn(messagesUtil.getMessage("message.not.found.user.with.name"), name, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DataAccessException e){
            logger.error(messagesUtil.getMessage("message.database.error"), e);
            return new ResponseEntity<>(messagesUtil.getMessage("message.response.database.error.response"), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @GetMapping(params = "email")
    public ResponseEntity<?> getUser(@RequestParam(value = "email") String email){
        try {
            logger.debug(messagesUtil.getMessage("message.email"), email);
            UserDTO user = userService.getByEmail(email);
            logger.debug(messagesUtil.getMessage("message.response"), user);
            logger.info(messagesUtil.getMessage("message.user.with.email"), email);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NotFoundUserException e) {
            logger.warn(messagesUtil.getMessage("message.not.found.user.with.email"), email, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DataAccessException e){
            logger.error(messagesUtil.getMessage("message.database.error"), e);
            return new ResponseEntity<>(messagesUtil.getMessage("message.response.database.error.response"), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserDTO user){
        logger.debug(messagesUtil.getMessage("message.request"), user);
        try {
            UserDTO newUser = userService.createUser(user);
            logger.debug(messagesUtil.getMessage("message.response"), newUser);
            logger.info(messagesUtil.getMessage("message.create.user.id"), newUser.getId());
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (EmailDuplicateException e) {
            logger.warn(messagesUtil.getMessage("message.user.email.duplication"), user.getEmail(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (DataAccessException e){
            logger.error(messagesUtil.getMessage("message.database.error"), e);
            return new ResponseEntity<>(messagesUtil.getMessage("message.response.database.error.response"), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long userId, @Valid @RequestBody UserDTO user){
        try {
            logger.debug(messagesUtil.getMessage("message.id.request"), userId, user);
            UserDTO updateUser = userService.updateUser(userId, user);
            logger.debug(messagesUtil.getMessage("message.response"), updateUser);
            logger.info(messagesUtil.getMessage("message.success.update.user.id"), userId);
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
        } catch (NotFoundUserException e) {
            logger.warn(messagesUtil.getMessage("message.not.found.user.with.id"), userId, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (EmailDuplicateException e) {
            logger.warn(messagesUtil.getMessage("message.user.email.duplication"), user.getEmail(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (DataAccessException e){
            logger.error(messagesUtil.getMessage("message.database.error"), e);
            return new ResponseEntity<>(messagesUtil.getMessage("message.response.database.error.response"), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long userId){
        try {
            logger.debug(messagesUtil.getMessage("message.id"), userId);
            userService.deleteUser(userId);
            logger.info(messagesUtil.getMessage("message.success.delete.user.id"), userId);
            return new ResponseEntity<>(messagesUtil.getMessage("message.response.success.delete.user.id") + userId, HttpStatus.OK);
        } catch (NotFoundUserException e) {
            logger.warn(messagesUtil.getMessage("message.not.found.user.with.id"), userId, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DataAccessException e){
            logger.error(messagesUtil.getMessage("message.database.error"), e);
            return new ResponseEntity<>(messagesUtil.getMessage("message.response.database.error.response"), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
