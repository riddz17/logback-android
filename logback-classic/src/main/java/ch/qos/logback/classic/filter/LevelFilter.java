package ch.qos.logback.classic.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * A class that filters events depending on their level.
 * 
 * One can specify a level and the behaviour of the filter when 
 * said level is encountered in a logging event.
 *
 * @author Ceki G&uuml;lc&uuml;
 * @author S&eacute;bastien Pennec
 */
public class LevelFilter extends AbstractMatcherFilter {

  Level level;
  
  @Override
  public FilterReply decide(Object eventObject) {
    if (!isStarted()) {
      return FilterReply.NEUTRAL;
    }
    
    LoggingEvent event = (LoggingEvent)eventObject;
    
    if (event.getLevel().equals(level)) {
      return onMatch;
    } else {
      return onMismatch;
    }
  }
  
  public void setLevel(String level) {
    this.level = Level.toLevel(level);
  }
  
  public void start() {
    if (this.level != null) {
      super.start();
    }
  }
}