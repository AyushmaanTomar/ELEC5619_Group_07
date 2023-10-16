import React from 'react';

function HomePage() {
  return (
    <>
      <h2 className="my-8 text-2xl font-bold">Home</h2>
      {/* <p>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Ut non,
        quisquam placeat fuga officiis quia, voluptatem earum temporibus eaque
        similique commodi libero doloremque! Vel asperiores culpa consequuntur
        sint deleniti veritatis? Lorem ipsum dolor sit amet consectetur
        adipisicing elit. Possimus, ratione. Repellendus maxime sit ducimus
        temporibus inventore consequuntur delectus sequi, dignissimos et modi
        beatae consectetur sed ullam aut explicabo culpa id.
      </p> */}
      <div
        className="hero min-h-screen"
        style={{
            backgroundImage : 'url(/assets/Usyd.jpg)',
        }}
      >
        
        <div className="hero-overlay bg-opacity-60"></div>
        <div className="hero-content text-center text-neutral-content">
          <div className="max-w-md">
            <h1 className="mb-5 text-5xl font-bold">Hello there</h1>
            <p className="mb-5">
              Our aim is to create a centralized platform for students to exchange goods and promote environmental sustainability by requiring university email verification, facilitating waste reduction, and offering potential expansion into additional student-focused services.
            </p>
            <form action='/products/'>
              <button className="btn btn-natural">Get Started</button>
            </form>
          </div>
        </div>
      </div>
    </>
  );
}

export default HomePage;