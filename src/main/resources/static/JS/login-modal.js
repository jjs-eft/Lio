{
  let login_modal = `<form action="" method="POST">
  <div class="login-modal" id="open-login-modal">
    <div class="login-modal__content modal-content">
      <a href="#">
        <img
          class="modal-content__close"
          src="/img/close_white_24dp.svg"
          alt="modal-close"
        />
      </a>
      <input
        type="email"
        placeholder="이메일"
        class="modal-content__input"
      />
      <input
        type="password"
        placeholder="비밀번호"
        class="modal-content__input"
      />
      <div class="modal-content_ly1">
        <a
          href="#login-modal-find"
          class="modal-content__box modal-content__box_small"
          >비밀번호 찾기</a
        >
        <a
          href="#login-modal-signup"
          class="modal-content__box modal-content__box_small"
          >회원가입</a
        >
      </div>
      <input
        type="submit"
        class="modal-content__box modal-content__box_big modal-content__first-big-btn"
        value="로그인"
      />
      <a href="" class="modal-content__box modal-content__box_big modal-content__google"
        >구글로 로그인</a
      >
      <a href="" class="modal-content__box modal-content__box_big modal-content__naver"
        >네이버로 로그인</a
      >
    </div>
  </div>
</form>
<form action="/#open-login-modal" method="POST">
  <div class="login-modal" id="login-modal-find">
    <div class="login-modal__content modal-content">
      <a href="#open-login-modal">
        <img
          class="modal-content__prev"
          src="/img/keyboard_arrow_left_white_24dp.svg"
          alt="modal-prev"
        />
      </a>
      <a href="#">
        <img
          class="modal-content__close"
          src="/img/close_white_24dp.svg"
          alt="modal-close"
        />
      </a>
      <input
        type="email"
        placeholder="이메일"
        class="modal-content__input"
      />
      <input
        type="submit"
        class="modal-content__box modal-content__box_big modal-content__first-big-btn"
        value="비밀번호 초기화"
      />
    </div>
  </div>
</form>
 <form action="/login-modal-signup" method="POST">
      <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
  <div class="login-modal" id="login-modal-signup">
    <div class="login-modal__content modal-content">
      <a href="#open-login-modal">
        <img
          class="modal-content__prev"
          src="/img/keyboard_arrow_left_white_24dp.svg"
          alt="modal-prev"
        />
      </a>
      <a href="#">
        <img
          class="modal-content__close"
          src="/img/close_white_24dp.svg"
          alt="modal-close"
        />
      </a>
      <input
        type="email"
        name="email"
        placeholder="이메일"
        class="modal-content__input"
      />
      <input
        type="password"
        name="password"
        placeholder="비밀번호"
        class="modal-content__input"
      />
      <input
        type="text"
        name="name"
        placeholder="이름"
        class="modal-content__input"
      />
      <input
        type="text"
        name="nickname"
        placeholder="닉네임"
        class="modal-content__input"
      />
      <input
        type="submit"
        class="modal-content__box modal-content__box_big modal-content__first-big-btn"
        value="회원가입"
      />
    </div>
  </div>
</form>
<form action="" method="POST">
      <div
        class="login-modal"
        id="tech-modal"
      >
        <div class="login-modal__content modal-content">
          <a href="#">
            <img
              class="modal-content__close"
              src="/img/close_white_24dp.svg"
              alt="modal-close"
            />
          </a>
          <p class="modal-content__p">
            <span class="font-weight_600">사용자</span>의 팀 찾기 매핑을
            위한<span class="font-weight_600">기술</span>을 등록해주세요.
          </p>
          <label for="tech" class="modal-content__label">언어</label>
          <!-- tech select -->
          <select name="tech" id="tech" class="modal-content__input">
            <option value="선택없음">선택없음</option>
            <option value="Python">Python</option>
            <option value="Java">Java</option>
          </select>
          <div class="">
            <span class="tag"
              >Python<img
                src="/img/close_white_24dp.svg"
                class="tag__close-btn"
            /></span>
            <span class="tag"
              >Java<img
                src="/img/close_white_24dp.svg"
                class="tag__close-btn"
            /></span>
          </div>
          <input
            type="submit"
            class="
              modal-content__box
              modal-content__box_big
              modal-content__first-big-btn
            "
            value="등록"
          />
        </div>
      </div>
    </form>
    <form action="" method="POST">
      <div
        class="login-modal"
        id="modify-pw-modal"
      >
        <div class="login-modal__content modal-content">
          <a href="#">
            <img
              class="modal-content__close"
              src="/img/close_white_24dp.svg"
              alt="modal-close"
            />
          </a>
          <p class="modal-content__p">
            <span class="font-weight_600">비밀번호</span>를 <span class="font-weight_600">변경</span>하시겠습니까?
          </p>
          <input
            type="password"
            placeholder="기존 비밀번호 입력"
            class="modal-content__input"
          />
          <input
            type="password"
            placeholder="변경 비밀번호 입력"
            class="modal-content__input"
          />
          <input
            type="password"
            placeholder="변경 비밀번호 확인"
            class="modal-content__input"
          />
          <input
            type="submit"
            class="
              modal-content__box
              modal-content__box_big
              modal-content__first-big-btn
            "
            value="비밀번호 변경"
          />
        </div>
      </div>
    </form>`
  document.write(login_modal);
}
{
  let modal_login = document.querySelector("#open-login-modal");
  let modal_find = document.querySelector("#login-modal-find");
  let modal_signup = document.querySelector("#login-modal-signup");
  let modal_tech = document.querySelector('#tech-modal');
  let modal_modi_pw = document.querySelector('#modify-pw-modal');
  // let modal_close = document.querySelector(".modal-content__close");
  // modal_close.addEventListener("click", () => {
  //   modal.style.visiblity = "0";
  // });
  window.onclick = function (event) {
    if (event.target === modal_login) {
      // modal.style.visiblity = "0";
      window.location.href = "#";
    }
    else if (event.target === modal_find) {
      window.location.href = "#";
    }
    else if (event.target === modal_signup) {
      window.location.href = "#";
    }
    else if (event.target === modal_tech) {
      window.location.href = "#";
    }
    else if (event.target === modal_modi_pw) {
      window.location.href = "#";
    }
  };
  // let elem = document.querySelector(".nav__link_login");
  // elem.addEventListener("click", () => {
  //   modal.style.visiblity = "1";
  // });
}