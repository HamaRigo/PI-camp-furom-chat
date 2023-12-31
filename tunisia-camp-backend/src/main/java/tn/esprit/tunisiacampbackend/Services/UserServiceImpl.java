package tn.esprit.tunisiacampbackend.Services;

import tn.esprit.tunisiacampbackend.DAO.DTO.ToDtoConverter;
import tn.esprit.tunisiacampbackend.DAO.DTO.UserDTO;
import tn.esprit.tunisiacampbackend.exception.UsernameAlreadyUsedException;
import tn.esprit.tunisiacampbackend.DAO.Entities.User;
import tn.esprit.tunisiacampbackend.DAO.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO getDefaultUser() {
        User defaultUser = userRepository.findById(1L).orElse(null);
        if(defaultUser != null)
            return ToDtoConverter.userToDto(defaultUser);
        else
            return null;
    }

    @Override
    public User connect(User user) throws UsernameAlreadyUsedException {
        User dbUser = userRepository.findByUsername(user.getUsername());

        if (dbUser != null) {

            if (dbUser.getConnected()) {
                throw new UsernameAlreadyUsedException("This user is already connected: " + dbUser.getUsername());
            }

            dbUser.setConnected(true);
            return userRepository.save(dbUser);
        }

        user.setConnected(true);
        return userRepository.save(user);
    }

    @Override
    public User disconnect(User user) {
        if (user == null) {
            return null;
        }

        User dbUser = userRepository.findByUsername(user.getUsername());
        if (dbUser == null) {
            return user;
        }

        dbUser.setConnected(false);
        return userRepository.save(dbUser);
    }
}