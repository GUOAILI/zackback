package com.guoaili.zackback.service.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.guoaili.zackback.DTO.UserVo;
import com.guoaili.zackback.config.JwtTokenService;
import com.guoaili.zackback.entity.Role;
import com.guoaili.zackback.entity.User;
import com.guoaili.zackback.enumT.RoleType;
import com.guoaili.zackback.repository.RoleRepository;
import com.guoaili.zackback.repository.UserRepository;
import com.guoaili.zackback.service.UserService;

@Service
public class UserServiceImpl implements UserService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(UserVo user) {
        User zpd=new User();
        zpd.setUsername(user.getUsername());
        zpd.setPassword(passwordEncoder.encode(user.getPassword()));

        List<RoleType> zpd001=new ArrayList<>();
        if(user.getUsername().contains("zhiping")) {
            zpd001.add(RoleType.USER);
            zpd001.add(RoleType.HZP);
        }
        else if(user.getUsername().contains("chuxuan")){
            zpd001.add(RoleType.USER);
            zpd001.add(RoleType.LCX);
        }
        else if(user.getUsername().contains("minjuan")){
            zpd001.add(RoleType.USER);
            zpd001.add(RoleType.LMJ);
        }
        else if(user.getUsername().contains("lina")){
            zpd001.add(RoleType.USER);
            zpd001.add(RoleType.TLN);
        }
        else if(user.getUsername().contains("jiandong")){
            zpd001.add(RoleType.ADMIN);
        }
        else {
            zpd001.add(RoleType.USER);
        }
        Role[] role=new Role[zpd001.size()];
        for(int i=0;i<zpd001.size();i++){
            role[i]=roleRepository.findByRole(zpd001.get(i));
            if(role[i] == null){
                role[i] = checkRoleExist(zpd001.get(i));
            }  
        }
        zpd.setRoles(Arrays.asList(role));
        userRepository.save(zpd);
    }

    @Override
    public UserVo login(UserVo uv) {

        try{
            final Authentication authentication = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            uv.getUsername(),
                            uv.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch(Exception err){
            return null;
        }
        final User user = userRepository.findByUsername(uv.getUsername());
        String token= jwtTokenService.generateToken(uv.getUsername(),user.getRoles());

        // return the role to username 2024/7/4
        uv.setUsername(null);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            uv.setUsername("long");
        }
        uv.setPassword(token);
        return uv;
    }

    private Role checkRoleExist(RoleType rt){
        Role role = new Role();
        role.setRole(rt);
        // return roleRepository.save(role);
        return roleRepository.saveOrUpRole(role);
    }

    @Override
    public User getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername());
    }
    
}
