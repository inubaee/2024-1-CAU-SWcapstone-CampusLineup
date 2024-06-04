function updateLoginButton() {
    console.log('Attempting to update login button');
    const loginButton = document.getElementById('loginButton');
    const user = JSON.parse(localStorage.getItem('user'));
    console.log('User from localStorage:', user); // 로컬 스토리지 데이터 확인

    if (user && user.name) {
        console.log('Updating login button text to:', user.name); // 업데이트 확인
        loginButton.textContent = user.name;
        console.log('Login button text updated to:', loginButton.textContent); // 버튼 텍스트 확인
        loginButton.href = 'mypage.html';
    }
}

document.addEventListener("DOMContentLoaded", function() {
    $("#header-placeholder").load("header.html", function() {
        console.log("Header loaded"); // 헤더가 로드되었는지 확인
        updateLoginButton(); // 헤더가 로드된 후에 로그인 버튼 업데이트
    });
});
