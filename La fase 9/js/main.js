
(() => {
  const nav = document.querySelector("[data-nav]");
  const menuToggle = document.querySelector(".menu-toggle");
  const navLinks = document.querySelector(".nav-links");
  const progress = document.querySelector(".reading-progress span");
  const cursor = document.querySelector(".cursor-dot");

  const setNavState = () => {
    nav?.classList.toggle("is-scrolled", window.scrollY > 20);
    if (progress) {
      const doc = document.documentElement;
      const max = doc.scrollHeight - window.innerHeight;
      const value = max > 0 ? (window.scrollY / max) * 100 : 0;
      progress.style.width = `${value}%`;
    }
  };

  window.addEventListener("scroll", setNavState, { passive: true });
  setNavState();

  menuToggle?.addEventListener("click", () => {
    const open = !navLinks.classList.contains("is-open");
    navLinks.classList.toggle("is-open", open);
    menuToggle.classList.toggle("is-open", open);
    menuToggle.setAttribute("aria-expanded", String(open));
  });

  document.querySelectorAll(".nav-links a").forEach((link) => {
    link.addEventListener("click", () => {
      navLinks?.classList.remove("is-open");
      menuToggle?.classList.remove("is-open");
      menuToggle?.setAttribute("aria-expanded", "false");
    });
  });

  if (cursor && matchMedia("(pointer: fine)").matches) {
    window.addEventListener("pointermove", (event) => {
      cursor.style.transform = `translate3d(${event.clientX}px, ${event.clientY}px, 0)`;
    }, { passive: true });
  }

  const observer = new IntersectionObserver((entries) => {
    entries.forEach((entry) => {
      if (entry.isIntersecting) {
        entry.target.classList.add("is-visible");
        observer.unobserve(entry.target);
      }
    });
  }, { threshold: 0.16 });
  document.querySelectorAll(".reveal").forEach((el) => observer.observe(el));

  const buildLightbox = () => {
    const lightbox = document.createElement("div");
    lightbox.className = "lightbox";
    lightbox.innerHTML = `
      <button type="button" aria-label="Cerrar imagen">&times;</button>
      <figure>
        <img alt="">
        <figcaption></figcaption>
      </figure>`;
    document.body.appendChild(lightbox);
    const image = lightbox.querySelector("img");
    const caption = lightbox.querySelector("figcaption");
    const close = () => lightbox.classList.remove("is-open");

    lightbox.querySelector("button").addEventListener("click", close);
    lightbox.addEventListener("click", (event) => {
      if (event.target === lightbox) close();
    });
    window.addEventListener("keydown", (event) => {
      if (event.key === "Escape") close();
    });

    document.querySelectorAll("[data-lightbox]").forEach((trigger) => {
      trigger.addEventListener("click", (event) => {
        event.preventDefault();
        image.src = trigger.getAttribute("href");
        image.alt = trigger.dataset.caption || "";
        caption.textContent = trigger.dataset.caption || "";
        lightbox.classList.add("is-open");
      });
    });
  };
  buildLightbox();

  const customParticles = (host) => {
    const canvas = document.createElement("canvas");
    host.appendChild(canvas);
    const ctx = canvas.getContext("2d");
    const particles = [];
    let width = 0;
    let height = 0;
    let raf = 0;

    const resize = () => {
      width = canvas.width = host.offsetWidth * devicePixelRatio;
      height = canvas.height = host.offsetHeight * devicePixelRatio;
      canvas.style.width = `${host.offsetWidth}px`;
      canvas.style.height = `${host.offsetHeight}px`;
      const count = Math.max(38, Math.floor(host.offsetWidth / 20));
      particles.length = 0;
      for (let i = 0; i < count; i += 1) {
        particles.push({
          x: Math.random() * width,
          y: Math.random() * height,
          vx: (Math.random() - 0.5) * 0.55 * devicePixelRatio,
          vy: (Math.random() - 0.5) * 0.55 * devicePixelRatio,
          r: (Math.random() * 1.8 + 0.8) * devicePixelRatio,
        });
      }
    };

    const draw = () => {
      ctx.clearRect(0, 0, width, height);
      ctx.fillStyle = "rgba(0, 245, 255, 0.78)";
      ctx.strokeStyle = "rgba(168, 85, 247, 0.18)";
      particles.forEach((p, index) => {
        p.x += p.vx;
        p.y += p.vy;
        if (p.x < 0 || p.x > width) p.vx *= -1;
        if (p.y < 0 || p.y > height) p.vy *= -1;
        ctx.beginPath();
        ctx.arc(p.x, p.y, p.r, 0, Math.PI * 2);
        ctx.fill();
        for (let j = index + 1; j < particles.length; j += 1) {
          const q = particles[j];
          const dist = Math.hypot(p.x - q.x, p.y - q.y);
          if (dist < 145 * devicePixelRatio) {
            ctx.globalAlpha = 1 - dist / (145 * devicePixelRatio);
            ctx.beginPath();
            ctx.moveTo(p.x, p.y);
            ctx.lineTo(q.x, q.y);
            ctx.stroke();
            ctx.globalAlpha = 1;
          }
        }
      });
      raf = requestAnimationFrame(draw);
    };

    resize();
    draw();
    window.addEventListener("resize", () => {
      cancelAnimationFrame(raf);
      resize();
      draw();
    }, { passive: true });
  };

  const initParticles = async () => {
    const hosts = document.querySelectorAll("[data-particles]");
    for (const host of hosts) {
      if (window.tsParticles) {
        const id = `particles-${Math.random().toString(36).slice(2)}`;
        host.id = id;
        try {
          await window.tsParticles.load({
            id,
            options: {
              fullScreen: false,
              background: { color: "transparent" },
              fpsLimit: 60,
              particles: {
                number: { value: 70, density: { enable: true, area: 900 } },
                color: { value: ["#00f5ff", "#a855f7", "#7cff6b"] },
                links: { enable: true, color: "#00f5ff", opacity: 0.18, distance: 135 },
                move: { enable: true, speed: 0.65 },
                opacity: { value: 0.62 },
                size: { value: { min: 1, max: 3 } },
              },
              interactivity: {
                events: { onHover: { enable: true, mode: "repulse" } },
                modes: { repulse: { distance: 120, duration: 0.4 } },
              },
              detectRetina: true,
            },
          });
        } catch {
          customParticles(host);
        }
      } else {
        customParticles(host);
      }
    }
  };

  initParticles();
})();
