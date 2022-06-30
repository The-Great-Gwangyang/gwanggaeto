### React란?

- UI를 효과적으로 구축하기 위해 사용하는 자바스크립트 기반의 라이브러리이다.

- 리액트 이벤트는 카멜 케이스(Camel Case) 네이밍 규칙을 사용한다.

  



 JSX란!?

얼핏 HTML같지만 JS문법이다.



![image-20220615020101894](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220615020101894.png)

XML 형태로 코드 작성하면 babel이 JSX를 JS로 변환해준다.





또 하나의 태그로 감싸져 있어야 한다.

```react
import React from 'react';
import Hello from './Hello';

function App() {
  return (
    <div>
      <Hello />
      <input>
      <br>
    </div>
  );
}

export default App;
```

div 태그 대신 <> (fragment)를 많이 사용한다.

fragment는 브라우저상에서 별도의엘리먼트로 나타나지 않는다.





jsx문법에서 js변수 사용하기

```react
import React from 'react';
import Hello from './Hello';

function App() {
  const name = 'react';
  return (
    <>
      <Hello />
      <div>{name}</div>
    </>
  );
}

export default App;
```



jsx에서 css적용하기

App.css

```css
.gray-box {
  background: gray;
  width: 64px;
  height: 64px;
}
```

App.js

```react
import React from 'react';
import Hello from './Hello';
import './App.css';


function App() {
  const name = 'react';
  const style = {
    backgroundColor: 'black',
    color: 'aqua',
    fontSize: 24, // 기본 단위 px
    padding: '1rem' // 다른 단위 사용 시 문자열로 설정
  }

  return (
    <>
      <Hello />
      <div style={style}>{name}</div>
      <div className="gray-box"></div>
    </>
  );
}

export default App;
```



![image-20220615020951813](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220615020951813.png)





props 기본 사용

- properties의 준말로 어떤 값을 컴포넌트에게 전달할 때 사용

App.js

```react
import React from 'react';
import Hello from './Hello';

function App() {
  return (
      <>
    <Hello name="react" />
    <Hello name="고성진님" />  
      </>
  );
}

export default App;
```

Hello.js

```react
import React from 'react';

function Hello(props) {
  return <div>안녕하세요 {props.name}</div>
}

export default Hello;
```







default props 추가하기

Hello.js

```react
import React from 'react';

function Hello({ color, name }) {
  return <div style={{ color }}>안녕하세요 {name}</div>
}

Hello.defaultProps = {
  name: '이름없음'
}

export default Hello;
```

App.js

```react
import React from 'react';
import Hello from './Hello';

function App() {
  return (
    <>
      <Hello name="react" color="red"/>
      <Hello color="pink"/>
    </>
  );
}

export default App;
```





클래스형 추가

Hello.js

```react
import React, { Component } from 'react';

class Hello extends Component {
  render() {
    const { color, name} = this.props;
    return (
      <div style={{ color }}>
        안녕하세요 {name}
      </div>
    );
  }
}

Hello.defaultProps = {
  name: '이름없음'
};

export default Hello;
```

App.js

```react
import React, {Component} from 'react';
import Hello from './Hello';

class App extends Component {
  render() {
    return (
    	<>
      		<Hello name="react" color="red"/>
      		<Hello color="pink"/>
   		</>
    );
  }
} 

export default App;
```















![image-20220615021311556](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20220615021311556.png)



- Props란!?
  - Props는 속성(Property)의 약자
  - Props를 이용해서 리액트는 계층적으로 구성되기 때문에 사용자가 보는 화면는 웹 페이지 화면을 효과적으로 볼 수 있다.
  - 고정적인 데이터를 전달 할 때 사용한다.
  - 함수형 컨포넌트를 사용한다.
  - 부모에서 자식으로 데이터를 전달할 때 사용한다.
- State란?
  - 고정적인 데이터가 아닌 변경될 수 있는 데이터를 처리할 때 효율적을 사용가능하다.
  - 이 값을 변경해서 화면이 변경되면 render() 함수가 다시 실행되어 실제 화면에 적용해 준다.
  - 클래스형 컨포넌트를 사용한다.
- 특징은?
  - 선언적이다: 대화형 UI를 작성하기가 유리하다. 데이터가 변경되었을 때 효율적으로 렌더링을 수행할 수 있도록 함.
  - 컴포넌트 기반이다.: 캡슐화된 컨포넌트가 상태를 관리하고 UI를 효과적으로 구성할 수 있음.
- 장단점은?
  - 클라이언트 렌더링(웹 사이트에서 그때 그때 데이터를 받아오는 방식) 뿐만 아니라 서버 사이드 렌더링(필요한 데이터를 미리 받아 놓은 방식)도 지원함. 그래서 Ajax 등과 같은 비동기 방식과 비교했을 때 검색 엔진 최적화 등에 있어서 유리한 형태로 스스코드 작성하는 것이 가능.
    - 왜냐하면 자바스크립트를 검색엔진이 지원하지 않는 경우도 있기 때문에 서버 사이드 렌더링의 형태도 대비를 해놓아하는데 리액트는 서버 사이드 렌더링 또한 지원.





## State

-> props처럼 컴포넌트의 렌더링 결과물에 영향을 주는 데이터를 갖는 객체로, props는 컴포넌트에 전달되는 반면 state는 컴포넌트 안에서 관리가 된다.



```react
import React, {Component} from 'react';

class Counter extends Component{

    constructor(props){
        super(props); // constructor 정의 시 반드시 필요
        this.state = { // state의 초기화
            number:0
        };
    }

    render(){

        const { number } = this.state;
        return (
            <div>
                <h1>{number}</h1>
                <button
                    onClick={()=>{
                        this.setState({number : number+1})
                    }}
                >
                    +1
                </button>
            </div>
        );
    }
}

export default Counter;
```







## 함수형 vs 클래스



클래스:

react 기능을 전부 사용할 수 있다.

복잡하다



함수:

함수문법만 알면 사용 가능하다.

react기능에 제한적이다. ( 컴포넌트 내부에서 만들어 사용하는 state불가 )







## hooks