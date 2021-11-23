package com.group7.fruitswebsite.controller;

import com.group7.fruitswebsite.config.JwtTokenUtil;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.dto.JwtRequest;
import com.group7.fruitswebsite.dto.JwtResponse;
import com.group7.fruitswebsite.dto.UserResponse;
import com.group7.fruitswebsite.service.CartService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author duyenthai
 */
@RestController
@RequestMapping("/v1")
@CrossOrigin
public class JwtAuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserDetailsService userDetailsService;
    private CartService cartService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<ApiResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String accessToken = jwtTokenUtil.generateAccessToken(userDetails);
        ApiResponse.ApiResponseResult responseResult = new ApiResponse.ApiResponseResult();
        List<Object> data = new ArrayList<>();
        data.add(new JwtResponse(accessToken));
        data.add(new UserResponse(userDetails.getUsername(), userDetails.getAuthorities().stream().map(Object::toString).collect(Collectors.toList())));
        data.add(cartService.findAllCart(userDetails.getUsername()));
        responseResult.setData(data);
        return ApiResponseUtil.getBaseSuccessStatus(responseResult);
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

}
