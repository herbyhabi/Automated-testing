//package com.perficient.selenium.support;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//import java.util.concurrent.atomic.AtomicBoolean;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.events.WebDriverEventListener;
//
//import com.perficient.test.cat.catinspectweb.util.SystemUtil;
//import com.perficient.test.cat.catinspectweb.util.TestCaseBase;
//
///**
// * To force the drive closed when time out.
// *
// * @author tripp.hu
// * @date Nov 20, 2015
// *
// */
//public class TimeoutListener implements WebDriverEventListener, Runnable {
//
//	private Thread timerThread = new Thread(this, "TimerThread"); {
//		timerThread.start();
//	}
//	private AtomicInteger i = new AtomicInteger(-1);
//	
//	public AtomicBoolean deadFlag = new AtomicBoolean(false);
//	
//	public class Handler implements InvocationHandler {
//
//		@Override
//		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//
//			if (method.getName().contains("before")) {
//				i.set(0);
//			} else if (method.getName().contains("after")) {
//				i.set(-1);
//			}
//			return method.invoke(TimeoutListener.this, args);
//		}
//		
//		public WebDriverEventListener get() {
//			return (WebDriverEventListener) Proxy.newProxyInstance(
//					ClassLoader.getSystemClassLoader(), 
//					new Class[]{WebDriverEventListener.class}, 
//					this);
//		}
//
//	}
//	
//	@Override
//	public void run() {
//		while (!deadFlag.get()) {
//			try {
//			if (i.get() == -1) {
//				continue;
//			}
//			if (i.incrementAndGet() >= 300) {
//				TestCaseBase.log.error("System have been waiting for 5 mins. Timeout! Try to kill the driver.");
//				TestCaseBase.driver.quit();
//				SystemUtil.driverKiller();
//				break;
//			} else if (i.get() % 60 == 0) {
//				TestCaseBase.log.info("System have been waiting for " + i.get() / 60 + " min(s).");
//			}
//			Thread.sleep(1000);
//		} catch (Exception e) {
//			
//			}
//		}
//		timerThread.interrupt();
//	}
//
//	@Override
//	public void beforeNavigateTo(String url, WebDriver driver) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void afterNavigateTo(String url, WebDriver driver) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void beforeNavigateBack(WebDriver driver) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void afterNavigateBack(WebDriver driver) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void beforeNavigateForward(WebDriver driver) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void afterNavigateForward(WebDriver driver) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void afterFindBy(By by, WebElement element, WebDriver driver) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void beforeClickOn(WebElement element, WebDriver driver) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void afterClickOn(WebElement element, WebDriver driver) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void afterChangeValueOf(WebElement element, WebDriver driver) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void beforeScript(String script, WebDriver driver) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void afterScript(String script, WebDriver driver) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void onException(Throwable throwable, WebDriver driver) {
//		// TODO Auto-generated method stub
//
//	}
//
//
//
//}
