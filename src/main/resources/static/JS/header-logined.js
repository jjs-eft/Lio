{
  let header = `<header class="header">
  <a href="/" class="header__logo"
    ><img src="img/LIO_Link_In_One.svg" alt="LIO_logo"
  /></a>
  <input type="checkbox" name="chk" id="nav-toggle" />
  <label for="nav-toggle" class="open-menu-btn">
    <img src="/img/menu_black_24dp.svg" alt="menu-open-btn" />
  </label>
  <ul class="nav">
    <li class="nav__item">
      <a href="/service-introduction.html" class="nav__item-title">서비스소개</a>
    </li>
    <li class="nav__item" id="team">
      <a href="#team" class="nav__item-title">팀 찾기</a>
      <ul class="nav-sub">
        <li class="nav-sub__item">
          <a href="/study-find.html" class="nav__link"
            >스터디 찾기</a
          >
        </li>
        <li class="nav-sub__item">
          <a href="/project-find.html" class="nav__link"
            >프로젝트 찾기</a
          >
        </li>
      </ul>
    </li>
    <li class="nav__item" id="board">
      <a href="#board" class="nav__item-title">게시판</a>
      <ul class="nav-sub">
        <li class="nav-sub__item">
          <a href="/board-free.html" class="nav__link"
            >자유 게시판</a
          >
        </li>
        <li class="nav-sub__item">
          <a href="/board-question.html" class="nav__link"
            >질문 게시판</a
          >
        </li>
        <li class="nav-sub__item">
          <a href="/board-notice.html" class="nav__link">공지 사항</a>
        </li>
      </ul>
    </li>
    <li class="nav__item">
      <a href="/DataTrendServlet" class="nav__item-title">최근트렌드</a>
    </li>
    <li class="nav__item" id="user-info">
    <a href="#user-info" class="nav__item-title">회원 정보</a>
    <ul class="nav-sub">
      <li class="nav-sub__item">
        <a href="/user-info-modify.html" class="nav__link"
          >회원 정보 수정</a
        >
      </li>
      <li class="nav-sub__item">
        <a href="/ChatBoxServlet" class="nav__link"
          >메시지</a
        >
      </li>
      <li class="nav-sub__item">
        <a href="/logout" class="nav__link"
          >로그아웃</a
        >
      </li>
    </ul>
  </ul>
  <label for="nav-toggle" class="close-menu-btn">
    <img src="/img/close_black_24dp.svg" alt="menu-close-btn" />
  </label>
</header>`
document.write(header);
}