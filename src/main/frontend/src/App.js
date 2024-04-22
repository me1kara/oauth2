// src/main/frontend/src/App.js

import React from 'react';
import './SocialLogin.css'; // 스타일 파일 불러오기

function App() {
    return ( 
      <div className="login-container">
        <h1 className="title">로그인</h1>
        <form className="login-form" action="/login" method="post">
          <div className="form-group">
            <label htmlFor="username">아이디:</label>
            <input type="text" id="username" name="username" />
          </div>
          <div className="form-group">
            <label htmlFor="password">비밀번호:</label>
            <input type="password" id="password" name="password" />
          </div>
          <button type="submit" className="login-btn">로그인</button>
        </form>
        <div className="social-login-container">
          <p className="or-text">또는</p>
          <div className="social-login-buttons">
            <a href="/api/oauth2/authorization/google" className="social-login-btn google">
              <i className="fab fa-google"></i> 구글로 로그인
            </a>
            <a href="/api/oauth2/authorization/naver" className="social-login-btn naver">
              <i className="fab fa-naver"></i> 네이버로 로그인
            </a>
            <a href="/api/oauth2/authorization/kakao" className="social-login-btn kakao">
              <i className="fab fa-kakao"></i> 카카오로 로그인
            </a>
          </div>
        </div>
      </div>
    );
}

export default App;