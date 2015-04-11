class Point:
   def __init__(self, x, y):
      self.x = x
      self.y = y
   def viewport_to_world(self, viewport):
      return Point(self.x + viewport.left, self.y + viewport.top)
   def world_to_viewport(self, viewport):
      return Point(self.x - viewport.left, self.y - viewport.top)
   def distance_sq(self, p2):
      return (self.x - p2.x)**2 + (self.y - p2.y)**2
