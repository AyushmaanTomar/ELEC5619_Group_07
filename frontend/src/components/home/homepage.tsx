import React from 'react';

function HomePage() {
  return (
    <>
      <h2 className="my-8 text-2xl font-bold">Home</h2>
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