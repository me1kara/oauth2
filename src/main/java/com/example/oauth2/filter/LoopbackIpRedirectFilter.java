//package com.example.oauth2.filter;
//
//import java.io.IOException;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.server.ServletServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.util.UriComponents;
//import org.springframework.web.util.UriComponentsBuilder;
//
///**
// * This filter ensures that the loopback IP <code>127.0.0.1</code> is used to access the
// * application so that the sample works correctly, due to the fact that redirect URIs with
// * "localhost" are rejected by the Spring Authorization Server, because the OAuth 2.1
// * draft specification states:
// *
// * <pre>
// *     While redirect URIs using localhost (i.e.,
// *     "http://localhost:{port}/{path}") function similarly to loopback IP
// *     redirects described in Section 10.3.3, the use of "localhost" is NOT
// *     RECOMMENDED.
// * </pre>
// *
// * @author Steve Riesenberg
// * @see <a href=
// * "https://tools.ietf.org/html/draft-ietf-oauth-v2-1-01#section-9.7.1">Loopback Redirect
// * Considerations in Native Apps</a>
// */
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class LoopbackIpRedirectFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        if (request.getServerName().equals("localhost") && request.getHeader("host") != null) {
//            UriComponents uri = UriComponentsBuilder.newInstance()
//                    .scheme(request.getScheme())  // 요청의 프로토콜을 유지합니다.
//                    .host("127.0.0.1")
//                    .port(request.getServerPort())  // 요청의 포트를 유지합니다.
//                    .path(request.getRequestURI())  // 요청의 URI 경로를 유지합니다.
//                    .query(request.getQueryString())  // 요청의 쿼리 문자열을 유지합니다.
//                    .build();
//            response.sendRedirect(uri.toUriString());
//            return;
//        }
//        filterChain.doFilter(request, response);
//    }
//
//}
